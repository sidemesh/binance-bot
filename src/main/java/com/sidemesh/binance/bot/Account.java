package com.sidemesh.binance.bot;

import java.math.BigDecimal;

public class Account {

    public final String key;
    public final String secret;
    /**
     * 当前账号手续费
     * 0.1%
     * 0.1 / 100 = 0.0001
     * https://www.binance.com/zh-CN/fee/futureFee
     */
    public final BigDecimal serviceChargeRate = new BigDecimal("0.0001");

    public Account(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public static Account of(String key, String secret) {
        return new Account(key, secret);
    }

    public static Account fromEnv() {
        var k = System.getenv("API_KEY");
        var s = System.getenv("API_SECRET");

        return of(k, s);
    }

}
