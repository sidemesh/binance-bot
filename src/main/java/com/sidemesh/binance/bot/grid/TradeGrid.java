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
    @Getter
    private BigDecimal serviceCharge;

    public static TradeGrid generate(BigDecimal investAmount, BigDecimal serviceCharge, TradeGridBuilder tradeGridBuilder) {
        List<Grid> grids = tradeGridBuilder.create();
        TradeGrid tradeGrid = new TradeGrid(grids);
        tradeGrid.stepAmount = investAmount.divide(BigDecimal.valueOf(grids.size()), investAmount.scale() + 2, RoundingMode.HALF_UP);
        if (BigDecimal.TEN.compareTo(tradeGrid.getStepAmount()) > 0) {
            throw new IllegalArgumentException(String.format("invest Amount too small [invest=%s, step=%s]", investAmount, tradeGrid.stepAmount));
        }
        if (serviceCharge.compareTo(BigDecimal.ONE) >= 0 || serviceCharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("手续费输入有误，不在0到1之间");
        }
        // 手续费非0时 判断格子收益是否满足
        if (serviceCharge.compareTo(BigDecimal.ZERO) != 0) {
            Grid topGrid = tradeGrid.getTopGrid();
            BigDecimal sellAmount = tradeGrid.stepAmount.divide(topGrid.getLowPrice(), RoundingMode.HALF_UP)
                    .multiply(BigDecimal.ONE.subtract(serviceCharge))
                    .multiply(topGrid.getHighPrice())
                    .multiply(BigDecimal.ONE.subtract(serviceCharge));
            if (sellAmount.compareTo(tradeGrid.stepAmount) <= 0) {
                throw new IllegalArgumentException(String.format("格子收益不足抵扣属续费！[成本=%s 收益=%s]", tradeGrid.stepAmount, sellAmount));
            }
        }
        tradeGrid.serviceCharge = serviceCharge;
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
        topDummyGrid = new Grid(topGrid.getHighPrice(), topGrid.getHighPrice().add(new BigDecimal("99999999")));
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
        if (startOrder == topOrder) return gridIndexes[startOrder];
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

    public static void main(String[] args) {
        // 手续费 10%
        // 当前价格 1  10买的 9个 10.001 卖
        // (10 * 10%) * 1.01 > 10
        // 9 * 当前价格 *
        BigDecimal 成本 = BigDecimal.TEN;
        BigDecimal low = BigDecimal.valueOf(0.1);
        BigDecimal hig = BigDecimal.valueOf(0.12);

        BigDecimal serviceCharge = new BigDecimal("0.01");
        BigDecimal multiply = 成本.divide(low).multiply(BigDecimal.ONE.subtract(serviceCharge))
                .multiply(hig)
                .multiply(BigDecimal.ONE.subtract(serviceCharge));
        System.out.println(multiply);

    }
}
