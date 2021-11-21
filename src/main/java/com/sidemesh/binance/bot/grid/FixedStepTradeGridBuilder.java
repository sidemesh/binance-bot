package com.sidemesh.binance.bot.grid;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 固定网格大小的网格生成器
 * 已成本价为中心 上下生成固定金额宽度的网格
 */
@Getter
@Setter
public class FixedStepTradeGridBuilder implements TradeGridBuilder {
    private BigDecimal currPrice;
    private BigDecimal gridStepPrice;
    private int gridLevels;

    /**
     * 简单的网格生成器
     * @param currPrice 成本价格
     * @param gridStepPrice 网格间隔金额
     * @param gridLevels 网格层数量
     */
    public FixedStepTradeGridBuilder(BigDecimal currPrice, BigDecimal gridStepPrice, int gridLevels) {
        if (gridLevels <= 0) {
            throw new IllegalArgumentException("gridLevels must not less than 1");
        }
        this.currPrice = currPrice;
        this.gridStepPrice = gridStepPrice;
        this.gridLevels = gridLevels;
    }

    @Override
    public List<Grid> create() {
        List<Grid> grids = new ArrayList<>();
        BigDecimal currPrice = this.currPrice;
        // 上界网格
        for (int i = 0; i < gridLevels; i++) {
            BigDecimal highPrice = currPrice.add(gridStepPrice);
            Grid grid = new Grid(currPrice, highPrice);
            grids.add(grid);
            currPrice = highPrice;
        }
        // 下届网格
        currPrice = this.currPrice;
        for (int i = 0; i < gridLevels; i++) {
            BigDecimal lowPrice = currPrice.subtract(gridStepPrice);
            Grid grid = new Grid(lowPrice, currPrice);
            grids.add(grid);
            currPrice = lowPrice;
        }
        return grids;
    }
}
