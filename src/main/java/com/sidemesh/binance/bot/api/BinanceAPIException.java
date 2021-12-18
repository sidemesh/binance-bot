package com.sidemesh.binance.bot.api;

public class BinanceAPIException extends Exception {

    public final boolean isLimited;

    public BinanceAPIException(String message, Throwable throwable, boolean isLimited) {
        super(message, throwable);
        this.isLimited = isLimited;
    }

    public static BinanceAPIException message(String message) {
        return new BinanceAPIException(message, null, false);
    }

    public static BinanceAPIException error(Throwable throwable) {
        return new BinanceAPIException("unknown error", throwable, false);
    }

    public static BinanceAPIException limited() {
        return new BinanceAPIException("request limited!", null, true);
    }

    public boolean isLimited() {
        return isLimited;
    }

}
