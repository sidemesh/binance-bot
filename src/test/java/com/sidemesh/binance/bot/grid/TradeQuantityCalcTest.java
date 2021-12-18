package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.TradeQuantity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TradeQuantityCalcTest {
    private static TradeGrid tradeGrid;

    @BeforeAll
    public static void setUp() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(10), 10);
        tradeGrid = TradeGrid.generate(new BigDecimal(100), BigDecimal.valueOf(0.001), fixedBoundTradeGridBuilder);
    }

    // TODO
    @Test
    public void isQuantityCorrect() {
        TradeQuantity tradeQuantity = TradeQuantity.instanceOf(Symbol.REN_USDT, BigDecimal.ONE, tradeGrid.getStepAmount());
        Assertions.assertEquals(tradeQuantity.quantity.compareTo(BigDecimal.valueOf(10)), 0);

        tradeQuantity = TradeQuantity.instanceOf(Symbol.REN_USDT, BigDecimal.TEN, tradeGrid.getStepAmount());
        Assertions.assertEquals(tradeQuantity.quantity.compareTo(BigDecimal.valueOf(1)), 0);
    }

}
