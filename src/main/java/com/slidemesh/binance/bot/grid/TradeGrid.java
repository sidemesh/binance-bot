package com.slidemesh.binance.bot.grid;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 交易网格
 */
public class TradeGrid {
    /**
     * 成本价
     */
    @Getter private BigDecimal costPrice;

    /**
     * 网格
     */
    @Getter private List<Grid> grids;

    /**
     * 网格Map
     */
    private Map<Integer, Grid> gridMap;

    /**
     * 最底部的网格
     */
    @Getter private Grid bottomGrid;

    /**
     * 最高网格
     */
    @Getter  private Grid topGrid;

    public TradeGrid(BigDecimal costPrice, List<Grid> grids) {
        this.costPrice = costPrice;
        this.grids = grids;
        // 初始化序号
        grids.sort(Comparator.comparing(Grid::getLowPrice));
        int order = 1;
        for (Grid grid : grids) {
            grid.setOrder(order);
            order++;
        }
        // 找出顶部和底部
        bottomGrid = grids.get(1);
        topGrid = grids.get(order);
    }

    /**
     * 获取当前价格所处的网格
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
     * @param currPrice
     * @param startOrder
     * @param topOrder
     * @return
     */
    private Grid binarySearchGrids(BigDecimal currPrice, int startOrder, int topOrder) {
        if (startOrder == topOrder) {
            return gridMap.get(startOrder);
        }
        int mid = (topOrder - startOrder) / 2 + 1;
        Grid midGrid = gridMap.get(mid);
        if (currPrice.compareTo(midGrid.getLowPrice()) < 0) {
            return binarySearchGrids(currPrice, startOrder, mid - 1);
        } else if(currPrice.compareTo(midGrid.getHighPrice()) >= 0) {
            return binarySearchGrids(currPrice, mid + 1, topOrder);
        } else {
            return midGrid;
        }
    }
}
