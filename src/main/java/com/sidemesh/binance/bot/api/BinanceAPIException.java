package com.sidemesh.binance.bot.api;

public class BinanceAPIException extends Exception {

    public final boolean isLimited;

    public BinanceAPIException(Throwable cause) {
        super(cause);
        this.isLimited = false;
    }

    public BinanceAPIException(boolean isLimited) {
        this.isLimited = isLimited;
    }

    public static BinanceAPIException limited() {
        return new BinanceAPIException(true);
    }
}
