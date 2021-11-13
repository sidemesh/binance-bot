package com.slidemesh.binance.bot.grid;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class TradeGridTest {

    @Test
    public void tradeGridBuildTest() {
        BigDecimal costPrice = new BigDecimal("10.123");

        SimpleTradeGridBuilder simpleTradeGridBuilder = new SimpleTradeGridBuilder(costPrice, BigDecimal.valueOf(1), 5);
        TradeGrid tradeGrid = simpleTradeGridBuilder.create();

        List<Grid> grids = tradeGrid.getGrids();

        Assertions.assertEquals(costPrice, tradeGrid.getCostPrice());
        Assertions.assertEquals(10, grids.size());
        Assertions.assertEquals(costPrice, tradeGrid.getLastTradePrice());
    }

    @Test
    public void currPriceFallCorrectGrid() {
        BigDecimal costPrice = new BigDecimal("10.123");
        SimpleTradeGridBuilder simpleTradeGridBuilder = new SimpleTradeGridBuilder(costPrice, BigDecimal.valueOf(1), 5);
        TradeGrid tradeGrid = simpleTradeGridBuilder.create();

        // 成本金额 所在的格子
        Grid currFallGrid = tradeGrid.getCurrFallGrid(costPrice);
        Assertions.assertEquals(0, currFallGrid.getOrder());

        // 当前价格落到 第二个格子
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("11.2"));
        Assertions.assertEquals(new BigDecimal("11.123"), currFallGrid.getLowPrice());
    }
}
