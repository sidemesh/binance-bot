package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.grid.FixedBoundTradeGridBuilder;
import com.sidemesh.binance.bot.grid.Grid;
import com.sidemesh.binance.bot.grid.TradeGrid;
import com.sidemesh.binance.bot.util.TradeUtil;
import com.sidemesh.binance.bot.worker.BlockingQueueBotWorker;
import com.sidemesh.binance.bot.worker.BotWorker;
import com.sidemesh.binance.bot.worker.ConditionBotWorker;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
public class SimpleGridBot implements Bot, RealtimeStreamListener {
    // 机器人名称
    private final String name;
    // 交易对
    private final Symbol symbol;
    // 网格
    private final TradeGrid tradeGrid;
    // 币安 API 接口
    private final BinanceAPI binanceAPI;
    // 交易所账号
    private final Account account;
    private volatile BotStatusEnum status = BotStatusEnum.STOP;
    // 上一次成交网格
    private Grid preTradeGrid;
    // bot执行线程
    private final BotWorker worker;
    // 当前价格 / 当前市场成交价格
    private RealtimeStreamData currentTrade;

    private final InvestInfo investInfo;
    private DealGridInfo dealGridInfo;

    public SimpleGridBot(String name,
                         Symbol symbol,
                         Account account,
                         BinanceAPI binanceAPI,
                         BigDecimal money,
                         BigDecimal lowPrice,
                         BigDecimal highPrice,
                         int grids,
                         RealtimeStream rts) {
        check(symbol, "symbol");
        check(account, "account");
        this.name = name;
        this.symbol = symbol;
        this.account = account;
        this.binanceAPI = binanceAPI;
        this.investInfo = new InvestInfo(money, BigDecimal.ZERO);
        this.tradeGrid = TradeGrid.generate(money, new FixedBoundTradeGridBuilder(lowPrice, highPrice, grids));
        this.worker = new ConditionBotWorker(name + "-worker");
        // 目测性能好一些，需要 benchmark
        // this.worker = new BlockingQueueBotWorker(name + "-worker");
        this.dealGridInfo = new DealGridInfo();
        rts.addListener(symbol, this);
    }

    private void check(Object o, String field) {
        if (o == null) {
            throw new IllegalArgumentException("missing " + field);
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Symbol symbol() {
        return symbol;
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
//                log.info("{} bot worker busy, abandon update event {}", name, data.id());
            }
        }
    }

    private void onPriceUpdate(RealtimeStreamData data) {
        if (currentTrade != null && currentTrade.id() > data.id()) {
            return;
        }
        currentTrade = data;
        final var price = data.price();
        // 没有游标设置游标
        if (preTradeGrid == null) {
            // 确定当前所处格子
            preTradeGrid = tradeGrid.getCurrFallGrid(price);
            return;
        }

        try {
            Grid currFallGrid = tradeGrid.getCurrFallGrid(price);
            if (currFallGrid != null) {
                doTrade(price, currFallGrid);
            }
        } catch (BinanceAPIException e) {
            if (e.isLimited) {
                log.info("api call limited! update data = {}", data);
            } else {
                log.error("api call error", e);
            }
        }
    }

    private void doTrade(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        if (preTradeGrid == currFallGrid) {
            return;
        }
        log.info("Bot {} {} {} 当前价格 {} 当前格子#{} --> 进入格子#{}", name, symbol, investInfo, price, preTradeGrid.getOrder(), currFallGrid.getOrder());
        int preTradeOrder = preTradeGrid.getOrder();
        // 判断是否为空仓
        if (currFallGrid.getOrder() > preTradeOrder) {
            if (investInfo.isEmptyQuantity()) {
                preTradeGrid = currFallGrid;
            } else {
                sell(price, currFallGrid);
            }
        } else if (currFallGrid.getOrder() < preTradeOrder) {
            buy(price, currFallGrid);
        }
    }

    private void sell(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        final var builder = OrderRequest.newLimitOrderBuilder();
        var option = dealGridInfo.packCanSells(currFallGrid);
        if (option.isEmpty()) {
            return;
        }
        var packed = option.get();
        // 判断当前持仓数量 (卖出的数量大于当前持仓 卖出全部持仓)
        OrderRequest req = builder
                .sell()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(packed.quantity())
                .timestamp(Instant.now().getEpochSecond())
                .rcwindow(3000L)
                .build();
        log.info("Bot {} {} 卖出 数量 {}", name, symbol, packed.quantity());
        Order order = binanceAPI.order(account, req);
        if (order.isDeal()) {
            OrderRequest request = order.getRequest();
            var resp = order.getResponse();
            BigDecimal executedQty = resp.getExecutedQty();
            BigDecimal sellAmount = executedQty.multiply(resp.getPrice());
            BigDecimal income = sellAmount.subtract(packed.totalPrice());
            investInfo.sellSome(sellAmount, executedQty, income);
            preTradeGrid = currFallGrid;
            dealGridInfo.onSell(packed);
            log.info("bot {} {} 卖出成功! 交易数量 {}  {}", name, symbol, executedQty, investInfo.getInfo());
        } else {
            log.info("bot {} {} 卖出失败! ", name, symbol);
        }
    }

    private void buy(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        OrderRequest.LimitOrderBuilder builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal buyGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity buyTrade = TradeQuantity.instanceOf(symbol, price, tradeGrid.getStepAmount().multiply(buyGridCount));
        OrderRequest req = builder
                .buy()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(buyTrade.quantity)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, buyTrade.quantity, buyTrade.amount);
        Order order = binanceAPI.order(account, req);
        if (order.isDeal()) {
            OrderRequest request = order.getRequest();
            BigDecimal executedQty = order.getResponse().getExecutedQty();
            investInfo.buySome(buyTrade.amount, executedQty);
            dealGridInfo.onBuy(currFallGrid, price, buyTrade.quantity);
            preTradeGrid = currFallGrid;
            log.info("bot {} {} 买入成功! 交易数量 {}  {}", name, symbol, executedQty, investInfo.getInfo());
        } else {
            log.info("bot {} {} 买入失败! ", name, symbol);
        }
    }

}
