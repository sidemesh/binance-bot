package com.sidemesh.binance.bot;

import java.math.BigDecimal;

/**
 * 投资信息
 */
public class InvestInfo {
    // 投资金额
    private BigDecimal invest;
    // 持仓数量
    private BigDecimal positQuantity;

    /**
     * @param invest        投资总额
     * @param positQuantity 持仓数量
     */
    public InvestInfo(BigDecimal invest, BigDecimal positQuantity) {
        if (invest == null || BigDecimal.ZERO.compareTo(invest) > 0) {
            throw new IllegalArgumentException("invest must big than zero");
        }
        if (positQuantity == null || BigDecimal.ZERO.compareTo(positQuantity) > 0) {
            throw new IllegalArgumentException("positQuantity must big than zero");
        }
        this.invest = invest;
        this.positQuantity = positQuantity;
    }

    /**
     * 买入
     *
     * @param amount
     * @param quantity
     */
    public void buySome(BigDecimal amount, BigDecimal quantity) {
        invest = invest.subtract(amount);
        positQuantity = positQuantity.add(quantity);
    }

    /**
     * 卖出
     *
     * @param amount
     * @param quantity
     */
    public void sellSome(BigDecimal amount, BigDecimal quantity) {
        invest = invest.add(amount);
        positQuantity = positQuantity.subtract(quantity);
    }

    /**
     * 是否空仓
     *
     * @return
     */
    public boolean isEmptyQuantity() {
        return BigDecimal.ZERO.compareTo(positQuantity) == 0;
    }

    public BigDecimal getInvest() {
        return invest;
    }

    public BigDecimal getPositQuantity() {
        return positQuantity;
    }

    @Override
    public String toString() {
        return "投资额剩余=" + invest +
                ",持仓=" + positQuantity;
    }

    public String getInfo(BigDecimal price) {
        final var totalInvest = invest.add(positQuantity.multiply(price));
        return "投资额剩余=" + invest +
                ",持仓=" + positQuantity +
                ",净收益=" + totalInvest.subtract(invest);
    }
}
