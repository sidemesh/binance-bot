package com.slidemesh.binance.bot.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易网格
 */
@Getter
@AllArgsConstructor
public class TradeGrid {
    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 最后一次交易价格
     */
    private BigDecimal lastTradePrice;

    /**
     * 最后一次交易时的网格
     */
    private Grid lastTradeGrid;

    /**
     * 网格
     */
    private List<Grid> grids;


    /**
     * 获取当前价格所处的网格
     * @param currPrice
     * @return
     */
    public Grid getCurrFallGrid(BigDecimal currPrice) {
        for (Grid grid : grids) {
            if (grid.isFallGrid(currPrice)) {
                return grid;
            }
        }
        return null;
    }
}
