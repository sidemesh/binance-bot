package com.sidemesh.binance.bot.api;

public class BinanceAPIException extends Exception {

    public final boolean isLimited;
    public final BinanceAPIV3ErrorResponse errorResponse;

    public BinanceAPIException(boolean isLimited, BinanceAPIV3ErrorResponse errorResponse) {
        this.isLimited = isLimited;
        this.errorResponse = errorResponse;
    }

    public BinanceAPIException(String message, boolean isLimited) {
        super(message);
        this.isLimited = isLimited;
        this.errorResponse = null;
    }

    public BinanceAPIException(Throwable cause) {
        super(cause);
        this.isLimited = false;
        this.errorResponse = null;
    }

    public BinanceAPIException(boolean isLimited) {
        this.isLimited = isLimited;
        this.errorResponse = null;
    }

    public static BinanceAPIException limited() {
        return new BinanceAPIException(true);
    }
}
