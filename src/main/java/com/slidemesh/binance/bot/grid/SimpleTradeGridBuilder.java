package com.slidemesh.binance.bot.grid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SimpleTradeGridBuilder implements TradeGridBuilder {
    private BigDecimal costPrice;
    private BigDecimal gridStepPrice;
    private int gridLevels;

    /**
     * 简单的网格生成器
     * @param costPrice 成本价格
     * @param gridStepPrice 网格间隔金额
     * @param gridLevels 网格层数量
     */
    public SimpleTradeGridBuilder(BigDecimal costPrice, BigDecimal gridStepPrice, int gridLevels) {
        if (gridLevels <= 0) {
            throw new IllegalArgumentException("gridLevels must not less than 1");
        }
        this.costPrice = costPrice;
        this.gridStepPrice = gridStepPrice;
        this.gridLevels = gridLevels;
    }

    @Override
    public TradeGrid create() {
        List<Grid> grids = new ArrayList<>();
        int order = 0;
        BigDecimal currPrice = costPrice;
        // 购买时所处的网格
        Grid costGrid = null;
        // 上界网格
        for (int i = 0; i < gridLevels; i++) {
            BigDecimal highPrice = currPrice.add(gridStepPrice);
            Grid grid = new Grid(order, currPrice, highPrice);
            grids.add(grid);
            order++;
            currPrice = highPrice;
            if (i == 0) {
                costGrid = grid;
            }
        }
        // 下届网格
        // 上界网格
        currPrice = costPrice;
        for (int i = 0; i < gridLevels; i++) {
            BigDecimal lowPrice = currPrice.subtract(gridStepPrice);
            Grid grid = new Grid(order, lowPrice, currPrice);
            grids.add(grid);
            order++;
            currPrice = lowPrice;
        }
        return new TradeGrid(costPrice, costPrice, costGrid, grids);
    }
}
