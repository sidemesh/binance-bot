package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.grid.Grid;
import com.sidemesh.binance.bot.grid.TradeGrid;
import com.sidemesh.binance.bot.util.TradeUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class SimpleGridBot implements Bot {
    private String name;
    private Symbol symbol;
    private BigDecimal serviceChargeRate;
    private TradeGrid tradeGrid;
    private BinanceAPI binanceAPI = new BinanceAPIv3();
    private Account account;
    private BotStatusEnum status = BotStatusEnum.STOP;
    /**
     * 持仓数量
     */
    private BigDecimal positQuantity;
    /**
     * 上一次成交网格
     */
    private Grid preTradeGrid;

    /**
     * 订单处理标志位
     * 0：无订单处理中
     * 1：有订单正在处理中
     */
    int orderProcessingFlag = 0;

    public SimpleGridBot(String name, Symbol symbol, BigDecimal serviceChargeRate, TradeGrid tradeGrid, Account account, BigDecimal positQuantity) {
        this.name = name;
        this.symbol = symbol;
        this.serviceChargeRate = serviceChargeRate;
        this.tradeGrid = tradeGrid;
        this.account = account;
        this.positQuantity = positQuantity;
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
        // run
        status = BotStatusEnum.RUNNING;
    }

    @Override
    public void stop() {
        status = BotStatusEnum.STOP;
    }

    @Override
    public void onPriceUpdate(Object data) {
        if (status != BotStatusEnum.RUNNING) {
            return;
        }
        // todo get price
        BigDecimal price = new BigDecimal("1.999");

        try {
            doTrade(price);
        } catch (BinanceAPIException e) {
            log.error("执行交易发生异常 ", e);
        }
    }

    private void doTrade(BigDecimal price) throws BinanceAPIException {
        Grid currFallGrid = tradeGrid.getCurrFallGrid(price);
        if (currFallGrid != null) {
            log.info("Bot {} {} 当前持仓数量 {} 当前价格 {} 进入格子 #{}", name, price, positQuantity, price, currFallGrid.getOrder());
            if (preTradeGrid == null) {
                // 第一次建仓
                log.info("Bot {} {} 开始第一次建仓....", name, symbol);
                buy(price, currFallGrid);
            } else {
                int preTradeOrder = preTradeGrid.getOrder();
                if (currFallGrid.getOrder() > preTradeOrder) {
                    // 卖出
                    sell(price, currFallGrid);
                } else if (currFallGrid.getOrder() < preTradeOrder) {
                    // 买入
                    buy(price, currFallGrid);
                }
            }
        }
    }

    private void sell(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        final var builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal sellGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity sellTrade = TradeQuantity.instanceOf(price, tradeGrid.getStepAmount().subtract(sellGridCount));
        // 判断当前持仓数量
        if (positQuantity.compareTo(sellTrade.getQuantity()) < 0) {
            return;
        }
        OrderRequest req = builder
                .sell()
                .id(TradeUtil.generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(sellTrade.getQuantity())
                .timestamp(new Date().getTime())
                .rcwindow(100L)
                .build();
        log.info("Bot {} {} 卖出 数量 {} 金额 {}", name, symbol, sellTrade.getQuantity(), sellTrade.getAmount());
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
                .quantity(buyTrade.getQuantity())
                .timestamp(new Date().getTime())
                .rcwindow(100L)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, buyTrade.getQuantity(), buyTrade.getAmount());
            Order order = binanceAPI.order(account, req);
            doHandleOrder(order, currFallGrid);
    }


    /**
     * 处理交易结果
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
