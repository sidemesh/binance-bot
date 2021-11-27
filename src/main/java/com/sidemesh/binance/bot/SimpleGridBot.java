package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.grid.FixedBoundTradeGridBuilder;
import com.sidemesh.binance.bot.grid.Grid;
import com.sidemesh.binance.bot.grid.TradeGrid;
import com.sidemesh.binance.bot.util.TradeUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;

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
    // 手续费
    private BigDecimal serviceChargeRate;
    // 投资金额
    private final BigDecimal money;
    // 持仓数量
    private BigDecimal positQuantity;
    // 可用资金
    private BigDecimal availableMoney;

    public SimpleGridBot(String name,
                         Symbol symbol,
                         Account account,
                         BigDecimal serviceChargeRate,
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
        this.money = money;
        this.serviceChargeRate = serviceChargeRate;
        this.positQuantity = BigDecimal.ZERO;
        this.availableMoney = money;
        this.tradeGrid = TradeGrid.generate(money, new FixedBoundTradeGridBuilder(lowPrice, highPrice, grids));
        this.worker = new BotWorker(name + "-worker");
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
            if (!worker.submit(() -> onPriceUpdate(data), false)) {
                log.info("worker has task! abandon price update {}", data);
            }
        }
    }

    private void onPriceUpdate(RealtimeStreamData data) {
        // 跳过延迟的 ID
        if (null == currentTrade || data.id() > currentTrade.id()) {
            currentTrade = data;
        } else {
            return;
        }
        final var price = data.price();
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
                log.info("api call limited! message = {}", data);
            } else {
                log.error("api call error", e);
            }
        }
    }

    private void doTrade(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        log.info("Bot {} {} 当前持仓数量 {} 当前价格 {} 进入格子 #{}", name, symbol, positQuantity, price, currFallGrid.getOrder());
        int preTradeOrder = preTradeGrid.getOrder();
        if (currFallGrid.getOrder() > preTradeOrder) {
            // 卖出
            sell(price, currFallGrid);
        } else if (currFallGrid.getOrder() < preTradeOrder) {
            // 买入
            buy(price, currFallGrid);
        }
    }

    private void sell(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        final var builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal sellGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity sellTrade = TradeQuantity.instanceOf(price, tradeGrid.getStepAmount().subtract(sellGridCount));
        // 判断当前持仓数量
        if (positQuantity.compareTo(sellTrade.quantity) < 0) {
            return;
        }
        OrderRequest req = builder
                .sell()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(sellTrade.quantity)
                .timestamp(Instant.now().getEpochSecond())
                .rcwindow(3000L)
                .build();
        log.info("Bot {} {} 卖出 数量 {} 金额 {}", name, symbol, sellTrade.quantity, sellTrade.quantity);
        Order order = binanceAPI.order(account, req);
        doHandleOrder(order, currFallGrid);
    }

    private void buy(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        OrderRequest.LimitOrderBuilder builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal buyGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity buyTrade = TradeQuantity.instanceOf(price, tradeGrid.getStepAmount().subtract(buyGridCount));
        OrderRequest req = builder
                .buy()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(buyTrade.quantity)
                .timestamp(Instant.now().getEpochSecond())
                .rcwindow(3000L)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, buyTrade.quantity, buyTrade.quantity);
        Order order = binanceAPI.order(account, req);
        doHandleOrder(order, currFallGrid);
    }


    /**
     * 处理交易结果
     *
     * @param order
     * @param fallGrid
     */
    private void doHandleOrder(Order order, Grid fallGrid) {
        if (order.isDeal()) {
            OrderRequest request = order.getRequest();
            BigDecimal executedQty = order.getResponse().getExecutedQty();
            log.info("bot {} {} 交易成功! 交易数量 {}", name, symbol, executedQty);
            if (request.side == OrderRequest.Side.BUY) {
                positQuantity = positQuantity.add(executedQty);
            } else {
                positQuantity = positQuantity.subtract(executedQty);
            }
            preTradeGrid = fallGrid;
        } else {
            log.info("bot {} {} 交易失败! ", name, symbol);
        }
    }
}
