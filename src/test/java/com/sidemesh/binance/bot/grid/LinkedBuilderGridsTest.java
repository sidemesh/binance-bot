package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class LinkedBuilderGridsTest {

    @Test
    public void testBuilder() {
        var builder = newBasicBuilder();

        // 测试网格数量不足
        builder.setGrids(1);
        try {
            builder.buildLinkedGrids();
            Assertions.fail("未抛出网格数量不足异常");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 测试投资金额不足
        builder = newBasicBuilder();
        builder.setInvest(new BigDecimal(1));
        try {
            builder.buildLinkedGrids();
            Assertions.fail("未抛出投资金额异常");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 测试收益不足的情况
        builder = newBasicBuilder();
        builder.setInvest(new BigDecimal(100));
        builder.setGrids(10);
        builder.setLow(new BigDecimal("0.00000001"));
        builder.setHigh(new BigDecimal("0.00000002"));

        try {
            builder.buildLinkedGrids();
            Assertions.fail("未抛出收益不足的情况异常");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 测试错误顶部和底部
        builder = newBasicBuilder();
        builder.setLow(new BigDecimal(100));
        try {
            builder.buildLinkedGrids();
            Assertions.fail("未抛出错误参数异常");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    public Builder newBasicBuilder() {
        var builder = Builder.newBuilder();
        builder.setGrids(10);
        builder.setAccount(Account.fromEnv());
        builder.setLow(new BigDecimal(1));
        builder.setHigh(new BigDecimal(10));
        builder.setInvest(new BigDecimal(100));
        builder.setSymbol(Symbol.SHIB_USDT);

        return builder;
    }


}
