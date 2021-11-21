package com.sidemesh.binance.bot.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TradeGridCalcTest {
    private static TradeGrid tradeGrid;

    @BeforeAll
    public static void setUp() {
        FixedBoundTradeGridBuilder fixedBoundTradeGridBuilder = new FixedBoundTradeGridBuilder(BigDecimal.valueOf(1), BigDecimal.valueOf(10), 10);
        tradeGrid =  TradeGrid.generate(new BigDecimal(100), fixedBoundTradeGridBuilder);
    }

    @Test
    public void isQuantityCorrect() {
        BigDecimal qualityByPrice = tradeGrid.getQualityByPrice(BigDecimal.valueOf(1));
        Assertions.assertEquals(qualityByPrice.compareTo(BigDecimal.valueOf(10)), 0);

        qualityByPrice = tradeGrid.getQualityByPrice(BigDecimal.valueOf(10));
        Assertions.assertEquals(qualityByPrice.compareTo(BigDecimal.valueOf(1)), 0);

        qualityByPrice = tradeGrid.getQualityByPrice(new BigDecimal("5.12311"));
//        Assertions.assertEquals(qualityByPrice, BigDecimal.valueOf(10));
        System.out.println(qualityByPrice);


    }

}
