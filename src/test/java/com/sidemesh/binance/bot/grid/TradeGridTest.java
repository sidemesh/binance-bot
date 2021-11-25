package com.sidemesh.binance.bot.grid;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class TradeGridTest {

    @Test
    public void fixedStepTradeGridBuildTest() {
        BigDecimal costPrice = new BigDecimal("10");

        FixedStepTradeGridBuilder fixedStepTradeGridBuilder = new FixedStepTradeGridBuilder(costPrice, BigDecimal.valueOf(1), 5);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(100), fixedStepTradeGridBuilder);

        List<Grid> grids = tradeGrid.getGrids();

        Assertions.assertEquals(10, grids.size());
        Assertions.assertEquals(BigDecimal.valueOf(5), tradeGrid.getBottomGrid().getLowPrice());
        Assertions.assertEquals(BigDecimal.valueOf(15), tradeGrid.getTopGrid().getHighPrice());
    }

    @Test
    public void fixedBoundTradeGridBuild() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(20), 20);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(100), fixedBoundTradeGridBuilder);

        List<Grid> grids = tradeGrid.getGrids();

        Assertions.assertEquals(20, grids.size());
        Assertions.assertEquals(BigDecimal.valueOf(1), tradeGrid.getBottomGrid().getLowPrice());
        Assertions.assertEquals(BigDecimal.valueOf(20), tradeGrid.getTopGrid().getHighPrice());
    }

    @Test
    public void currPriceFallCorrectGrid() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(20), 20);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(100), fixedBoundTradeGridBuilder);

        // 成本金额 #10
        Grid currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("10"));
        Assertions.assertEquals(10, currFallGrid.getOrder());

        // 当前价格落到 #11
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("11.2"));
        Assertions.assertEquals(11, currFallGrid.getOrder());

        // 当前价格落到 #20
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("19.05"));
        Assertions.assertEquals(20, currFallGrid.getOrder());

        // 当前价格落到 #1
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("1.2"));
        Assertions.assertEquals(1, currFallGrid.getOrder());

        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("30"));
        Assertions.assertNull(currFallGrid);
    }
}
