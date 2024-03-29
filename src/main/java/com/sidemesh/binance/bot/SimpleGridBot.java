package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.grid.Builder;
import com.sidemesh.binance.bot.grid.LinkedGrids;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;
import com.sidemesh.binance.bot.util.TradeUtil;
import com.sidemesh.binance.bot.websocket.event.BookTickerMessage;
import com.sidemesh.binance.bot.worker.BotWorker;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Slf4j
public class SimpleGridBot extends BaseBot implements RealtimeStreamListener {
    // 实时数据流
    private final RealtimeStream rts;
    // 币安 API 接口
    private final BinanceAPI binanceAPI;
    // 交易所账号
    private final Account account;
    // 网格
    private final LinkedGrids grids;
    // bot 运行状态 flag
    private volatile BotStatus status = BotStatus.STOP;
    // bot执行线程
    private final BotWorker worker;
    // 当前价格 / 当前市场成交价格
    private RealtimeStreamData currentTrade;
    // 投资盈利信息
    private final InvestInfo investInfo;
    // 格子交易记录
    private final DealGridInfo dealGridInfo;
    // 持久化服务
    private final StoreService storeService = new StoreServiceJsonFileImpl();

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

        this.rts = rts;
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
        this.worker = createConditionBotWorker();
        this.dealGridInfo = new DealGridInfo();
        // 持久化
        storeService.save(this);
    }

    /**
     * 通过持久化的数据 创建bot
     * @param botStat 数据
     */
    public SimpleGridBot(BotStat botStat,
                         BinanceAPI binanceAPI,
                         Account account,
                         RealtimeStream rts) {
        super(botStat.getName(), botStat.getSymbol());

        log.info("recover bot [{}] from bot stat", botStat.name);

        check(botStat, "botStat");
        check(account, "account");

        this.rts = rts;
        this.account = account;
        this.binanceAPI = binanceAPI;
        this.investInfo = new InvestInfo(botStat.getIncomeTotal(), botStat.getSurplusInvest(), botStat.getPositQuantity());
        this.grids = Builder.newBuilder()
                .setGrids(botStat.getGrids())
                .setLow(botStat.getLow())
                .setHigh(botStat.getHigh())
                .setAccount(account)
                .setSymbol(symbol)
                .setInvest(botStat.getInvest())
                .buildLinkedGrids();
        grids.resetIndex(botStat.getOrder());
        this.grids.print();
        if (botStat.buyGrids != null) {
            this.dealGridInfo = new DealGridInfo(
                    botStat.buyGrids.stream()
                            .map(v -> new DealGridInfo.DealGrid(grids.indexOf(v.order), v.price, v.quantity))
                            .collect(Collectors.toList())
            );
        } else {
            this.dealGridInfo = new DealGridInfo();
        }
        this.worker = createConditionBotWorker();
    }

    private void check(Object o, String field) {
        if (o == null) throw new IllegalArgumentException("missing " + field);
    }

    @Override
    public void start() {
        if (isRunning()) {
            log.warn("bot already start!");
            return;
        }

        log.warn("start bot {}", name);
        // 启动 worker
        worker.start();
        // 添加监听器
        rts.addListener(symbol, this);
        // 设置 bot 状态
        status = BotStatus.RUNNING;
    }

    public boolean isRunning() {
        return status == BotStatus.RUNNING;
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            log.warn("bot {} already stopped!", name);
            return;
        }

        log.info("stop bot {}", name);
        rts.removeListener(symbol, this);
        worker.stop();
        status = BotStatus.STOP;
    }

    @Override
    public BotStatus status() {
        return status;
    }

    @Override
    public void update(RealtimeStreamData data) {
        if (isRunning()) {
            // System.out.println(data);
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
                log.error("binance api error" , e);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            final var resp = order.getResponse();
            final var executedQty = resp.getExecutedQty();
            BigDecimal sellAmount = executedQty.multiply(resp.getPrice());
            BigDecimal income = sellAmount.subtract(packed.totalPrice());
            investInfo.sellSome(sellAmount, executedQty, income);
            ir.updateIndex();
            dealGridInfo.onSell(packed);
            storeService.update(this);
            log.info("bot {} {} 卖出成功! 交易数量 {}  {}", name, symbol, executedQty, investInfo.getInfo());
        } else {
            log.info("bot {} {} 卖出失败! 订单状态 {} ", name, symbol, order.getResponse().getStatus());
            // 尝试使用最优价格进行交易
            this.tryUseOrderBookBestPriceTrade(latestBookTicker.bestSellPrice);
        }
    }

    private void buy(BigDecimal price, LinkedGrids.IndexResult ir) throws BinanceAPIException {
        OrderRequest.LimitOrderBuilder builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal buyGridCount = ir.orderOffset();
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
            final var resp = order.getResponse();
            final var origQty = resp.getOrigQty();
            final var receiveQty = resp.receivedQty();
            investInfo.buySome(tq.amount, receiveQty);
            dealGridInfo.onBuy(ir.newIndex, price, receiveQty);
            ir.updateIndex();
            storeService.update(this);
            log.info("bot {} {} 买入成功! 下单数量 {} 手续费 {} 到账数量 {} {}", name, symbol,
                    origQty, resp.serviceCharge(), receiveQty,
                    investInfo.getInfo());
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


    @Override
    public BotStat stat() {
        return BotStat.builder()
                .name(name)
                .symbol(symbol)
                .status(status)
                .low(grids.info.low)
                .high(grids.info.high)
                .grids(grids.info.grids)
                .order(grids.getIndex().order)
                .invest(grids.info.invest)
                .surplusInvest(investInfo.getInvest())
                .positQuantity(investInfo.getPositQuantity())
                .incomeTotal(investInfo.getIncomeTotal())
                .buyGrids(dealGridInfo.getDealGridList().stream().map(v -> {
                            BotStat.BuyGrid buyGrid = new BotStat.BuyGrid();
                            buyGrid.order = v.grid.order();
                            buyGrid.price = v.price;
                            buyGrid.quantity = v.quantity;
                            return buyGrid;
                        }).collect(Collectors.toList())
                )
                .build();
    }


}
