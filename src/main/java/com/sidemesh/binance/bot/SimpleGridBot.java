package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.grid.*;
import com.sidemesh.binance.bot.util.TradeUtil;
import com.sidemesh.binance.bot.websocket.event.BookTickerMessage;
import com.sidemesh.binance.bot.worker.BotWorker;
import com.sidemesh.binance.bot.worker.ConditionBotWorker;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SimpleGridBot extends BaseBot implements Bot, RealtimeStreamListener {
    // 网格
    private final LinkedGrids grids;
    // 币安 API 接口
    private final BinanceAPI binanceAPI;
    // 交易所账号
    private final Account account;
    // bot 运行状态 flag
    private volatile BotStatusEnum status = BotStatusEnum.STOP;
    // bot执行线程
    private final BotWorker worker;
    // 当前价格 / 当前市场成交价格
    private RealtimeStreamData currentTrade;
    // 投资盈利信息
    private final InvestInfo investInfo;
    // 格子交易记录
    private final DealGridInfo dealGridInfo;

    // -------------------- 最优价格相关逻辑
    // 最优买卖价格
    private volatile BookTickerMessage latestBookTicker;
    // 是否已尝试过使用最优价格交易
    private boolean isTriedUseBestPriceTrade;
    // ---------------------- 最优价格相关逻辑

    public SimpleGridBot(String name,
                         Symbol symbol,
                         Account account,
                         BinanceAPI binanceAPI,
                         BigDecimal invest,
                         BigDecimal lowPrice,
                         BigDecimal highPrice,
                         int grids,
                         RealtimeStream rts) {
        super(name, symbol);

        check(symbol, "symbol");
        check(account, "account");

        this.account = account;
        this.binanceAPI = binanceAPI;
        this.investInfo = new InvestInfo(invest, BigDecimal.ZERO);
        this.grids = Builder.newBuilder()
                .setGrids(grids)
                .setLow(lowPrice)
                .setHigh(highPrice)
                .setAccount(account)
                .setSymbol(symbol)
                .setInvest(invest)
                .buildLinkedGrids();
        this.grids.print();
        this.worker = new ConditionBotWorker(name + "-worker");
        // 目测性能好一些，需要 benchmark
        // this.worker = new BlockingQueueBotWorker(name + "-worker");
        this.dealGridInfo = new DealGridInfo();
        // 添加监听器
        rts.addListener(symbol, this);
    }

    private void check(Object o, String field) {
        if (o == null) throw new IllegalArgumentException("missing " + field);
    }

    @Override
    public void run() {
        status = BotStatusEnum.RUNNING;
    }

    private boolean isRunning() {
        return status == BotStatusEnum.RUNNING;
    }

    @Override
    public void stop() {
        // 停止的时候是否卖出全部持仓？
        status = BotStatusEnum.STOP;
    }

    @Override
    public void update(RealtimeStreamData data) {
        if (isRunning()) {
            if (!worker.submit(() -> onPriceUpdate(data))) {
                log.info("{} bot worker busy, abandon event {} price {}", name, data.id(), data.price());
            }
        }
    }

    /*
     * 仅考虑线程可见性，有序性通过 ID 进行保证
     */
    @Override
    public void update(BookTickerMessage msg) {
        if (this.latestBookTicker == null || this.latestBookTicker.id < msg.id) {
            this.latestBookTicker = msg;
        }
    }

    private void onPriceUpdate(RealtimeStreamData data) {
        // 重置状态
        this.resetStates();

        if (currentTrade != null && currentTrade.id() > data.id()) return;
        currentTrade = data;
        final var price = data.price();
        this.onPriceUpdate(price);
    }

    private void onPriceUpdate(final BigDecimal price) {
        // 没有游标设置游标
        grids.try2Init(price);

        try {
            doTrade(price, grids.findIndex(price));
        } catch (BinanceAPIException e) {
            if (e.isLimited) {
                log.info("api call limited! update price = {}", price);
            } else if (e.isInsufficientBalance()) {
                log.info("api call balance insufficient!");
            } else {
                log.error("api call error: " + e.getMessage(), e);
            }
        }
    }

    private void doTrade(BigDecimal price, LinkedGrids.IndexResult ir) throws BinanceAPIException {
        // log.info("price = {} index = {} new index = {}", price, ir.index.order, ir.newIndex.order);
        // 格子没有变化直接返回
        if (ir.isRemain()) return;

        log.info("Bot {} {} {} 当前价格 {} 当前格子#{} --> 进入格子#{}", name, symbol, investInfo, price, ir.index.order, ir.newIndex.order);
        switch (ir.status) {
            case DOWN:
                buy(price, ir);
                break;
            case RISE:
                // 判断是否空仓，如果为空仓则更新索引
                if (investInfo.isEmptyQuantity()) ir.updateIndex();
                else sell(price, ir);
                break;
            default:
                log.warn("unknown result status {}", ir.status);
        }
    }

    private void sell(BigDecimal price, LinkedGrids.IndexResult ir) throws BinanceAPIException {
        var option = dealGridInfo.packCanSells(ir.newIndex);
        if (option.isEmpty()) return;
        var packed = option.get();
        // 判断当前持仓数量 (卖出的数量大于当前持仓 卖出全部持仓)
        final var builder = OrderRequest.newLimitOrderBuilder();
        OrderRequest req = builder
                .sell()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(packed.quantity())
                .build();
        log.info("Bot {} {} 卖出 数量 {}", name, symbol, packed.quantity());
        Order order = binanceAPI.order(account, req);
        if (order.isDeal()) {
            // OrderRequest request = order.getRequest();
            var resp = order.getResponse();
            BigDecimal executedQty = resp.getExecutedQty();
            BigDecimal sellAmount = executedQty.multiply(resp.getPrice());
            BigDecimal income = sellAmount.subtract(packed.totalPrice());
            investInfo.sellSome(sellAmount, executedQty, income);
            ir.updateIndex();
            dealGridInfo.onSell(packed);
            log.info("bot {} {} 卖出成功! 交易数量 {}  {}", name, symbol, executedQty, investInfo.getInfo());
        } else {
            log.info("bot {} {} 卖出失败! 订单状态 {} ", name, symbol, order.getResponse().getStatus());
            // 尝试使用最优价格进行交易
            this.tryUseOrderBookBestPriceTrade(latestBookTicker.bestSellPrice);
        }
    }

    private void buy(BigDecimal price, LinkedGrids.IndexResult ir) throws BinanceAPIException {
        OrderRequest.LimitOrderBuilder builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal buyGridCount = BigDecimal.valueOf(Math.abs(ir.index.order - ir.newIndex.order));
        TradeQuantity tq = TradeQuantity.instanceOf(symbol, price, grids.info.orderAmountOfPerGrid.multiply(buyGridCount));
        OrderRequest req = builder
                .buy()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(tq.quantity)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, tq.quantity, tq.amount);
        Order order = binanceAPI.order(account, req);
        if (order.isDeal()) {
            // OrderRequest request = order.getRequest();
            BigDecimal executedQty = order.getResponse().getExecutedQty();
            investInfo.buySome(tq.amount, executedQty);
            dealGridInfo.onBuy(ir.newIndex, price, tq.quantity);
            ir.updateIndex();
            log.info("bot {} {} 买入成功! 交易数量 {}  {}", name, symbol, executedQty, investInfo.getInfo());
        } else {
            log.info("bot {} {} 买入失败! 订单状态 {}", name, symbol, order.getResponse().getStatus());
            // 尝试使用最优价格进行交易
            this.tryUseOrderBookBestPriceTrade(latestBookTicker.bestBuyPrice);
        }
    }

    /*
     * 流程如下：
     * event -> doTrade -> trade failed -> best retry -> check is best retried -> [retry : abandon!]
     * ->  trade failed -> best retry ->  check is best retried -> done!
     */
    private void tryUseOrderBookBestPriceTrade(final BigDecimal price) {
        log.info("try use order book best price trade. flag = {} price = {}", isTriedUseBestPriceTrade, price);
        // 判断状态，防止重复递归
        if (isTriedUseBestPriceTrade) {
            // 此行可移除
            this.isTriedUseBestPriceTrade = false;
            return;
        }

        this.isTriedUseBestPriceTrade = true;
        onPriceUpdate(price);
    }

    private void resetStates() {
        this.isTriedUseBestPriceTrade = false;
    }

}
