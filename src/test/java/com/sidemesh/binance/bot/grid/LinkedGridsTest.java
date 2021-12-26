package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class LinkedGridsTest {

    @Test
    public void testCreate() {
        newLinkedGrids();
    }

    @Test
    public void testInitFlag() {
        var g = newLinkedGrids();
        Assertions.assertFalse(g.isInit());
        g.init(new BigDecimal("1.1"));
        Assertions.assertTrue(g.isInit());
    }

    @Test
    public void testInitIfOverflowLowPrice() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("1.1"));
        Assertions.assertSame(g.getHead(), g.getIndex());
    }

    @Test
    public void testInitIfOverflowHighPrice() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("8.0"));
        Assertions.assertSame(g.getTail(), g.getIndex());
    }

    @Test
    public void testInitBottom() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("4.4"));
        Assertions.assertSame(g.getHead(), g.getIndex());

        g = newLinkedGrids();
        g.init(new BigDecimal("4.41"));
        Assertions.assertSame(g.getHead(), g.getIndex());
    }

    @Test
    public void testInitTop() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("5.4"));
        Assertions.assertSame(g.getTail(), g.getIndex());

        g = newLinkedGrids();
        g.init(new BigDecimal("5.5"));
        Assertions.assertSame(g.getTail(), g.getIndex());
    }

    private LinkedGrids newLinkedGrids() {
        return Builder
                .newBuilder()
                .setGrids(10).setInvest(new BigDecimal("100"))
                .setLow(new BigDecimal("4.4"))
                .setHigh(new BigDecimal("5.4"))
                .setSymbol(Symbol.BICO_USDT)
                .setAccount(Account.fromEnv())
                .buildLinkedGrids();
    }

}
