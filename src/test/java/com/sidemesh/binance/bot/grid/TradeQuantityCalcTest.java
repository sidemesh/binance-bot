package com.sidemesh.binance.bot.grid;

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
        tradeGrid =  TradeGrid.generate(new BigDecimal(100), fixedBoundTradeGridBuilder);
    }

    @Test
    public void isQuantityCorrect() {
        TradeQuantity tradeQuantity = TradeQuantity.instanceOf(BigDecimal.ONE, tradeGrid.getStepAmount());
        Assertions.assertEquals(tradeQuantity.quantity.compareTo(BigDecimal.valueOf(10)), 0);

        tradeQuantity = TradeQuantity.instanceOf(BigDecimal.TEN, tradeGrid.getStepAmount());
        Assertions.assertEquals(tradeQuantity.quantity.compareTo(BigDecimal.valueOf(1)), 0);

        tradeQuantity = TradeQuantity.instanceOf(new BigDecimal("5.12311"), tradeGrid.getStepAmount());
        Assertions.assertEquals(tradeQuantity.quantity.compareTo(new BigDecimal("1.9519393")), 0);

    }

}
