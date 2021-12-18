package com.sidemesh.binance.bot.grid;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TradeGridTest {

    @Test
    public void fixedStepTradeGridBuildTest() {
        BigDecimal costPrice = new BigDecimal("10");

        FixedStepTradeGridBuilder fixedStepTradeGridBuilder = new FixedStepTradeGridBuilder(costPrice, BigDecimal.valueOf(1), 5);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(100), BigDecimal.valueOf(0.001), fixedStepTradeGridBuilder);

        List<Grid> grids = tradeGrid.getGrids();

        assertEquals(10, grids.size());
        assertEquals(BigDecimal.valueOf(5), tradeGrid.getBottomGrid().getLowPrice());
        assertEquals(BigDecimal.valueOf(15), tradeGrid.getTopGrid().getHighPrice());
    }

    @Test
    public void fixedBoundTradeGridBuild() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(20), 20);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(200), BigDecimal.valueOf(0.0001), fixedBoundTradeGridBuilder);

        List<Grid> grids = tradeGrid.getGrids();

        assertEquals(20, grids.size());
        assertEquals(BigDecimal.valueOf(1), tradeGrid.getBottomGrid().getLowPrice());
        assertEquals(BigDecimal.valueOf(20), tradeGrid.getTopGrid().getHighPrice());
    }

    @Test
    public void currPriceFallCorrectGrid() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(20), 20);
        TradeGrid tradeGrid = TradeGrid.generate(new BigDecimal(200), BigDecimal.valueOf(0.0001), fixedBoundTradeGridBuilder);

        // 成本金额 #10
        Grid currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("10"));
        assertEquals(10, currFallGrid.getOrder());

        // 当前价格落到 #11
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("11.2"));
        assertEquals(11, currFallGrid.getOrder());

        // 当前价格落到 #20
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("19.05"));
        assertEquals(20, currFallGrid.getOrder());

        // 当前价格落到 #1
        currFallGrid = tradeGrid.getCurrFallGrid(new BigDecimal("1.2"));
        assertEquals(1, currFallGrid.getOrder());
    }

    @Test
    public void canIllegalArgumentThrowException() {
        // 上下边界参数 反向
        assertThrows(IllegalArgumentException.class,
                () -> new FixedBoundTradeGridBuilder(BigDecimal.valueOf(20), BigDecimal.valueOf(10), 20));
        // 格子为负数
        assertThrows(IllegalArgumentException.class,
                () -> new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(10), -1));
    }
}
