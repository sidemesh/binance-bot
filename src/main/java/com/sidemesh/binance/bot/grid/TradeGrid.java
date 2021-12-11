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
     * 网格索引
     */
    private final Grid[] gridIndexes;

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
     * 顶部哑节点
     */
    private final Grid topDummyGrid;

    @Getter
    private BigDecimal stepAmount;
    /**
     * 哑节点
     */
    private final Grid topDummyGrid;

    public static TradeGrid generate(BigDecimal investAmount, TradeGridBuilder tradeGridBuilder) {
        List<Grid> grids = tradeGridBuilder.create();
        TradeGrid tradeGrid = new TradeGrid(grids);
        tradeGrid.stepAmount = investAmount.divide(BigDecimal.valueOf(grids.size()), investAmount.scale() + 2, RoundingMode.HALF_UP);
        if (BigDecimal.TEN.compareTo(tradeGrid.getStepAmount()) >= 0) {
            throw new IllegalArgumentException("invest Amount too small");
        }
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

        // 转换数组
        gridIndexes = new Grid[grids.size() + 1];
        // 由于 order 由 1 开始，所以 0 设为哑结点
        gridIndexes[0] = null;
        this.grids.forEach(g -> gridIndexes[g.getOrder()] = g);

        // 添加哑节点
        topDummyGrid = new Grid(topGrid.getHighPrice(),topGrid.getHighPrice().add(new BigDecimal("99999999")));
        topDummyGrid.setOrder(topGrid.getOrder() + 1);
    }

    /**
     * 获取当前价格所处的网格
     */
    public Grid getCurrFallGrid(BigDecimal currPrice) {
        if (currPrice.compareTo(bottomGrid.getLowPrice()) < 0) {
            return null;
        } else if (currPrice.compareTo(topGrid.getHighPrice()) >= 0) {
            return topDummyGrid;
        } else {
            return binarySearchGrids(currPrice, bottomGrid.getOrder(), topGrid.getOrder());
        }
    }

    /**
     * 二分查找
     */
    private Grid binarySearchGrids(BigDecimal currPrice, int startOrder, int topOrder) {
        if (startOrder == topOrder)  return gridIndexes[startOrder];
        int mid = (topOrder + startOrder) / 2 + 1;
        Grid midGrid = gridIndexes[mid];
        if (currPrice.compareTo(midGrid.getLowPrice()) < 0) {
            return binarySearchGrids(currPrice, startOrder, mid - 1);
        } else if (currPrice.compareTo(midGrid.getHighPrice()) >= 0) {
            return binarySearchGrids(currPrice, mid + 1, topOrder);
        } else {
            return midGrid;
        }
    }
}
