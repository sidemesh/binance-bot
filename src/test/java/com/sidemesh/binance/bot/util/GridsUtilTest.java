package com.sidemesh.binance.bot.util;

import com.sidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class GridsUtilTest {

    @Test
    public void testComputeGirdsSegment() {

        var l = new BigDecimal("10.123333");
        var h = new BigDecimal("23.7");
        var gs = 10;

        var s = GridsUtil.computeGirdsSegment(Symbol.SHIB_USDT, l, h, gs);
        Assertions.assertTrue(s.compareTo(new BigDecimal("0")) >= 0);
    }
}
