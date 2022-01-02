package com.sidemesh.binance.bot;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 交易金额
 */
public class TradeQuantity {
    public final BigDecimal quantity;
    public final BigDecimal amount;

    /**
     * @param symbolPrice 币种 当前价格
     * @param tradeAmount 购买金额
     */
    public static TradeQuantity instanceOf(Symbol symbol, BigDecimal symbolPrice, BigDecimal tradeAmount) {
        // 不能小，可以大
        return new TradeQuantity(tradeAmount.divide(symbolPrice, symbol.quantityPrecision.scale(), RoundingMode.UP),
                tradeAmount);
    }

    private TradeQuantity(BigDecimal quantity, BigDecimal amount) {
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
