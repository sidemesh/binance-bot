package com.slidemesh.binance.bot.grid;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 固定 上下边界 的网格生成器
 */
public class FixedBoundTradeGridBuilder implements TradeGridBuilder {
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 下边界
     */
    private BigDecimal lowerPrice;
    /**
     * 上边界
     */
    private BigDecimal upperPrice;
    /**
     * 网格数量
     */
    private int gridNumber;

    public FixedBoundTradeGridBuilder(BigDecimal costPrice, BigDecimal lowerPrice, BigDecimal upperPrice, int gridNumber) {
        this.costPrice = costPrice;
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        this.gridNumber = gridNumber;
    }

    @Override
    public TradeGrid create() {
        int scale = Math.max(lowerPrice.scale(), upperPrice.scale());

        BigDecimal subtract = upperPrice.subtract(lowerPrice);
        BigDecimal step = subtract.divide(BigDecimal.valueOf(gridNumber), scale + 2, RoundingMode.HALF_UP);

        BigDecimal low = lowerPrice;
        List<Grid> grids = new ArrayList<>(gridNumber + 1);
        for (int i = 0; i < gridNumber; i++) {
            BigDecimal high;
            if (i == gridNumber - 1) {
                high = upperPrice;
            } else {
                high = low.add(step);
            }
            Grid grid = new Grid(low, high);
            grids.add(grid);
            low = high;
        }
        return new TradeGrid(costPrice, grids);
    }
}
