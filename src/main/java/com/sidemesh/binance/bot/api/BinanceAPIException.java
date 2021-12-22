package com.sidemesh.binance.bot.api;

public class BinanceAPIException extends Exception {

    public final boolean isLimited;
    public final ResponseError responseError;

    public BinanceAPIException(String message,
                               Throwable throwable,
                               boolean isLimited,
                               ResponseError err) {
        super(message, throwable);
        this.isLimited = isLimited;
        this.responseError = err;
    }

    public BinanceAPIException(String message,
                               Throwable throwable,
                               boolean isLimited) {
        super(message, throwable);
        this.isLimited = isLimited;
        this.responseError = null;
    }

    public BinanceAPIException(ResponseError err) {
        this(err.toString(), null, false, err);
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

    public boolean isInsufficientBalance() {
        return this.responseError != null && responseError.getCode() == 1021;
    }

    public boolean isLimited() {
        return isLimited;
    }

}
