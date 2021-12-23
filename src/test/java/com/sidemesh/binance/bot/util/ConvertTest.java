package com.sidemesh.binance.bot.util;

import com.sidemesh.binance.bot.grid.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ConvertTest {

    @Test
    public void testConvertGirdToMap() {
        var g = new Grid(new BigDecimal("1"), new BigDecimal("2"));
        var m = Convert.toMap(g);
        Assertions.assertNotNull(m);
        var m2 = g.toMap();
        Assertions.assertNotNull(m2);
    }

}
