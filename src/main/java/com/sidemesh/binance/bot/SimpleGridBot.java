package com.sidemesh.binance.bot;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;
import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.grid.Grid;
import com.sidemesh.binance.bot.grid.TradeGrid;
import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.util.TradeUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.*;

@Slf4j
public class SimpleGridBot implements Bot {
    private String name;
    private Symbol symbol;
    private BigDecimal serviceChargeRate;
    private TradeGrid tradeGrid;
    private BinanceAPI binanceAPI;
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
    /**
     * 订单处理线程池
     */
    private ThreadPoolExecutor orderPool;

    public SimpleGridBot(String name, Symbol symbol, BigDecimal serviceChargeRate, TradeGrid tradeGrid, Account account, BigDecimal positQuantity) {
        check(symbol, "symbol");
        check(tradeGrid, "tradeGrid");
        check(account, "account");
        this.name = name;
        this.symbol = symbol;
        this.serviceChargeRate = serviceChargeRate;
        this.tradeGrid = tradeGrid;
        this.account = account;
        this.binanceAPI = new BinanceAPIv3();
        this.positQuantity = positQuantity == null ? BigDecimal.ZERO : positQuantity;
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        orderPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                // 阻塞队列为 1
                new ArrayBlockingQueue<>(1),
                builder.setNameFormat("bot-order-pool-%d").build(),
                // 丢弃并发的订单
                new ThreadPoolExecutor.DiscardPolicy());

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
        // run
        status = BotStatusEnum.RUNNING;
    }

    @Override
    public void stop() {
        status = BotStatusEnum.STOP;
    }

    // todo 暂不支持并发
    @Override
    public void onPriceUpdate(Object data) {
        if (status != BotStatusEnum.RUNNING) {
            return;
        }
        if (orderProcessingFlag == 1) {
            return;
        }

        // todo get price
        BigDecimal price = new BigDecimal("1.999");

        // 表示正在处理
        orderProcessingFlag = 1;
        orderPool.submit(() -> {
            try {
                Grid currFallGrid = tradeGrid.getCurrFallGrid(price);
                if (currFallGrid != null) {
                    doTrade(price, currFallGrid);
                }
            } catch (BinanceAPIException e) {
                log.error("执行交易发生异常 ", e);
            } finally {
                // 重置flag
                orderProcessingFlag = 0;
            }
        });
    }

    private void doTrade(BigDecimal price, Grid currFallGrid) throws BinanceAPIException {
        log.info("Bot {} {} 当前持仓数量 {} 当前价格 {} 进入格子 #{}", name, symbol, positQuantity, price, currFallGrid.getOrder());
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
