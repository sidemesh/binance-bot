package com.slidemesh.binance.bot;

public enum Symbol {

    LINK_USDT("linkusdt"),
    ETH_USDT("ethusdt"),
    BTC_USDT("btcusdt");

    public final String str;

    Symbol(String str) {
        this.str = str;
    }
}