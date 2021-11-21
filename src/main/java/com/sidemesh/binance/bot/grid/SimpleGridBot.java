package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.*;
import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.api.BinanceAPIv3;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private BigDecimal positQuantity = BigDecimal.ZERO;
    /**
     * 上一次成交网格
     */
    private Grid preTradeGrid;

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
        Grid currFallGrid = tradeGrid.getCurrFallGrid(price);
        if (currFallGrid != null) {
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

    private void sell(BigDecimal price, Grid currFallGrid) {
        final var builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal sellGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity sellTrade = TradeQuantity.instanceOf(price, tradeGrid.getStepAmount().subtract(sellGridCount));
        // 判断当前持仓数量
        if (positQuantity.compareTo(sellTrade.getQuantity()) < 0) {
            return;
        }
        final var req = builder
                .sell()
                .id(generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(sellTrade.getQuantity())
                .timestamp(new Date().getTime())
                .rcwindow(100L)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, sellTrade.getQuantity(), sellTrade.getAmount());
        try {
            Order order = binanceAPI.order(account, req);
            doHandleOrder(order, currFallGrid);
        } catch (BinanceAPIException e) {
            e.printStackTrace();
        }
    }

    private void buy(BigDecimal price, Grid currFallGrid) {
        OrderRequest.LimitOrderBuilder builder = OrderRequest.newLimitOrderBuilder();
        BigDecimal buyGridCount = BigDecimal.valueOf(Math.abs(currFallGrid.getOrder() - preTradeGrid.getOrder()));
        TradeQuantity buyTrade = TradeQuantity.instanceOf(price, tradeGrid.getStepAmount().subtract(buyGridCount));
        final var req = builder
                .buy()
                .id(generateTradeId())
                .symbol(symbol)
                .price(price)
                .quantity(buyTrade.getQuantity())
                .timestamp(new Date().getTime())
                .rcwindow(100L)
                .build();
        log.info("Bot {} {} 买入 数量 {} 金额 {}", name, symbol, buyTrade.getQuantity(), buyTrade.getAmount());
        try {
            Order order = binanceAPI.order(account, req);
            doHandleOrder(order, currFallGrid);
        } catch (BinanceAPIException e) {
            e.printStackTrace();
        }
    }

    private String generateTradeId() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }


    /**
     * 处理交易结果
     * @param order
     * @param fallGrid
     */
    private void doHandleOrder(Order order, Grid fallGrid) {
        if (order.isDeal()) {
            log.info("bot {} success!", name);
            OrderRequest request = order.getRequest();
            BigDecimal executedQty = order.getResponse().getExecutedQty();
            if (request.side == OrderRequest.Side.BUY) {
                positQuantity = positQuantity.add(executedQty);
            } else {
                positQuantity = positQuantity.subtract(executedQty);
            }
            preTradeGrid = fallGrid;
        } else {
            log.info("bot {} fail ", name);
        }
    }
}
