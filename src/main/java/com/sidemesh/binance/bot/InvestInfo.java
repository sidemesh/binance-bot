package com.sidemesh.binance.bot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 投资信息
 */
@NoArgsConstructor
public class InvestInfo {
    // 收益总金额
    @Getter
    private BigDecimal incomeTotal = BigDecimal.ZERO;
    // 投资金额
    private BigDecimal invest;
    // 持仓数量
    private BigDecimal positQuantity;

    public InvestInfo(BigDecimal incomeTotal, BigDecimal surplusInvest, BigDecimal positQuantity) {
        if (incomeTotal == null) {
            throw new IllegalArgumentException("incomeTotal must not be null");
        }
        if (surplusInvest == null) {
            throw new IllegalArgumentException("surplusInvest must not be null");
        }
        if (positQuantity == null) {
            throw new IllegalArgumentException("positQuantity must not be null");
        }
        this.incomeTotal = incomeTotal;
        this.invest = surplusInvest;
        this.positQuantity = positQuantity;
    }

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
     * @param income 卖出收益
     */
    public void sellSome(BigDecimal amount, BigDecimal quantity, BigDecimal income) {
        invest = invest.add(amount);
        positQuantity = positQuantity.subtract(quantity);
        incomeTotal = incomeTotal.add(income);
    }

    /**
     * 是否空仓
     *
     * @return
     */
    @JsonIgnore
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

    @JsonIgnore
    public String getInfo() {
        return "投资额剩余=" + invest +
                ",持仓=" + positQuantity +
                ",总收益=" + incomeTotal;
    }
}
