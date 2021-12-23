package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.util.Convert;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Grid implements Convert.ToMap {
    /**
     * 网格序号
     * 1 ---> max
     */
    @Setter
    @Getter
    private int order;
    /**
     * 网格下界
     */
    @Getter private final BigDecimal lowPrice;
    /**
     * 网格上界
     */
    @Getter private final BigDecimal highPrice;

    public Grid(BigDecimal lowPrice, BigDecimal highPrice) {
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
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
