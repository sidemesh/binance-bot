package com.sidemesh.binance.bot;

public class Account {

    public final String key;
    public final String secret;

    public Account(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public static Account of(String key, String secret) {
        return new Account(key, secret);
    }

}
