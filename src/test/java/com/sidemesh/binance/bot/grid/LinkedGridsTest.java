package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Symbol;
import org.apache.commons.lang3.RandomUtils;
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

    @Test
    public void testUpdateRandom() {
        for (var i = 0; i < 10000; i++) {
            var f = RandomUtils.nextFloat(0.1f, 5.9f);
            var g = newLinkedGrids();
            g.init(new BigDecimal(f));

            f = RandomUtils.nextFloat(0.1f, 5.9f);
            var res = g.findIndex(new BigDecimal(f));
            Assertions.assertNotNull(res);
        }
    }

    @Test
    public void testRemainUpdate() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("4.4"));

        var res = g.findIndex(new BigDecimal("4.4"));
        Assertions.assertTrue(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertFalse(res.isRise());
    }

    @Test
    public void testRemainUpdate2() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("4.4"));

        var res = g.findIndex(new BigDecimal("4.41"));
        Assertions.assertTrue(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertFalse(res.isRise());
    }

    @Test
    public void testDownUpdate() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("5.2"));

        var res = g.findIndex(new BigDecimal("4.41"));
        Assertions.assertFalse(res.isRemain());
        Assertions.assertTrue(res.isDown());
        Assertions.assertFalse(res.isRise());
    }

    @Test
    public void testRise() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("4.5"));

        var res = g.findIndex(new BigDecimal("5.3"));
        Assertions.assertFalse(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertTrue(res.isRise());
    }

    @Test
    public void testUpdateIndex() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("4.5"));

        var res = g.findIndex(new BigDecimal("5.3"));
        Assertions.assertFalse(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertTrue(res.isRise());

        res.updateIndex();
        Assertions.assertSame(g.getIndex(), res.newIndex);
    }

    @Test
    public void testRiseTopOverflow() {
        var g = newLinkedGrids();
        g.init(new BigDecimal("5.1"));
        var res = g.findIndex(new BigDecimal("5.4"));
        Assertions.assertFalse(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertTrue(res.isRise());
        // now is tail
        res.updateIndex();

        res = g.findIndex(new BigDecimal("99"));
        Assertions.assertTrue(res.isRemain());
        Assertions.assertFalse(res.isDown());
        Assertions.assertFalse(res.isRise());
    }

    private LinkedGrids newLinkedGrids() {
        return Builder
                .newBuilder()
                .setGrids(10).setInvest(new BigDecimal("110"))
                .setLow(new BigDecimal("4.4"))
                .setHigh(new BigDecimal("5.4"))
                .setSymbol(Symbol.BICO_USDT)
                .setAccount(Account.fromEnv())
                .buildLinkedGrids();
    }

}
