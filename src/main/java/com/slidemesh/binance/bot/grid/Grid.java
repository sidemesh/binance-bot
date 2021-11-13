package com.slidemesh.binance.bot.grid;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Grid {
    /**
     * 网格序号
     */
    private int order;
    /**
     * 网格下界
     */
    private BigDecimal lowPrice;
    /**
     * 网格上界
     */
    private BigDecimal highPrice;

    /**
     * 是否落在了此网格上
     * @param currPrice
     * @return
     */
    public boolean isFallGrid(BigDecimal currPrice) {
        if (lowPrice.compareTo(currPrice) <= 0
                && highPrice.compareTo(currPrice) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Grid{" +
                "#" + order +
                " lowPrice=" + lowPrice +
                " highPrice=" + highPrice +
                '}';
    }
}
