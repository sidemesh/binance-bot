package com.sidemesh.binance.bot.dto;

import com.sidemesh.binance.bot.Symbol;

import java.math.BigDecimal;

public class CreateBotRequest {

    private String name;
    private Symbol symbol;
    private BigDecimal amountUSDT;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;
    private Integer grids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAmountUSDT() {
        return amountUSDT;
    }

    public void setAmountUSDT(BigDecimal amountUSDT) {
        this.amountUSDT = amountUSDT;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getGrids() {
        return grids;
    }

    public void setGrids(Integer grids) {
        this.grids = grids;
    }
}
