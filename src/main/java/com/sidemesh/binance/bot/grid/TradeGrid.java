package com.sidemesh.binance.bot.grid;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 交易网格
 */
public class TradeGrid {

    /**
     * 网格
     */
    @Getter
    private final List<Grid> grids;

    /**
     * 网格Map
     */
    private final Map<Integer, Grid> gridMap;

    /**
     * 最底部的网格
     */
    @Getter
    private final Grid bottomGrid;

    /**
     * 最高网格
     */
    @Getter
    private final Grid topGrid;
    /**
     * 投资总额
     */
    @Getter
    private BigDecimal investAmount;

    @Getter
    private BigDecimal stepAmount;

    public static TradeGrid generate(BigDecimal investAmount, TradeGridBuilder tradeGridBuilder) {
        List<Grid> grids = tradeGridBuilder.create();
        TradeGrid tradeGrid = new TradeGrid(grids);
        // todo 校验最小投入总金额
        tradeGrid.investAmount = investAmount;
        tradeGrid.stepAmount = investAmount.divide(BigDecimal.valueOf(grids.size()), RoundingMode.HALF_UP);
        return tradeGrid;
    }

    private TradeGrid(List<Grid> grids) {
        this.grids = grids;
        // 初始化序号
        grids.sort(Comparator.comparing(Grid::getLowPrice));
        int order = 1;
        for (Grid grid : grids) {
            grid.setOrder(order);
            order++;
        }
        // 找出顶部和底部
        bottomGrid = grids.get(0);
        topGrid = grids.get(grids.size() - 1);
        // 转换 Map
        gridMap = grids.stream().collect(Collectors.toMap(Grid::getOrder, v -> v, (o1, o2) -> o1));
    }

    /**
     * 获取交易网格 每格购买数量
     * @param currPrice
     * @return
     */
    public BigDecimal getQualityByPrice(BigDecimal currPrice) {
        int scale = Math.max(currPrice.scale(), stepAmount.scale());
        return stepAmount.divide(currPrice, scale + 2, RoundingMode.HALF_UP);
    }

    /**
     * 获取当前价格所处的网格
     *
     * @param currPrice
     * @return
     */
    public Grid getCurrFallGrid(BigDecimal currPrice) {
        if (currPrice.compareTo(bottomGrid.getLowPrice()) < 0) {
            return null;
        } else if (currPrice.compareTo(topGrid.getHighPrice()) >= 0) {
            return null;
        } else {
            return binarySearchGrids(currPrice, bottomGrid.getOrder(), topGrid.getOrder());
        }
    }

    /**
     * 二分查找
     *
     * @param currPrice
     * @param startOrder
     * @param topOrder
     * @return
     */
    private Grid binarySearchGrids(BigDecimal currPrice, int startOrder, int topOrder) {
        if (startOrder == topOrder) {
            return gridMap.get(startOrder);
        }
        int mid = (topOrder + startOrder) / 2 + 1;
        Grid midGrid = gridMap.get(mid);
        if (currPrice.compareTo(midGrid.getLowPrice()) < 0) {
            return binarySearchGrids(currPrice, startOrder, mid - 1);
        } else if (currPrice.compareTo(midGrid.getHighPrice()) >= 0) {
            return binarySearchGrids(currPrice, mid + 1, topOrder);
        } else {
            return midGrid;
        }
    }
}
