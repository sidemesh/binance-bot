package com.sidemesh.binance.bot.api;

public class BinanceAPIException extends Exception {

    public final boolean isLimited;
    public final ResponseError errorResponse;

    public BinanceAPIException(String message, Throwable throwable, boolean isLimited, ResponseError errorResponse) {
        super(message, throwable);
        this.isLimited = isLimited;
        this.errorResponse = errorResponse;
    }

    public static BinanceAPIException message(String message) {
        return new BinanceAPIException(message, null, false, null);
    }

    public static BinanceAPIException error(Throwable throwable) {
        return new BinanceAPIException("unknown error", throwable, false, null);
    }

    public static BinanceAPIException errorResponse(ResponseError e) {
        return new BinanceAPIException("api response error", null, false, e);
    }

    public static BinanceAPIException limited() {
        return new BinanceAPIException("request limited!", null, true, null);
    }

    public boolean isLimited() {
        return isLimited;
    }

    public boolean isErrorResponse() {
        return errorResponse != null;
    }

}
