package com.sidemesh.binance.bot;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 交易金额
 */
@Getter
public class TradeQuantity {
    private final BigDecimal quantity;
    private final BigDecimal amount;

    /**
     *
     * @param symbolPrice 币种 当前价格
     * @param tradeAmount 购买/卖出金额
     * @return
     */
    public static TradeQuantity instanceOf(BigDecimal symbolPrice, BigDecimal tradeAmount) {
        int scale = Math.max(symbolPrice.scale(), tradeAmount.scale());
        return new TradeQuantity(tradeAmount.divide(symbolPrice, scale + 2, RoundingMode.HALF_UP),
                tradeAmount);
    }

    public TradeQuantity(BigDecimal quantity, BigDecimal amount) {
        this.quantity = quantity;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TradeQuantity{" +
                "quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
