package com.slidemesh.binance.bot.binance;

public class BaseMethodMessage extends BaseMessage {

    public String method;

    public BaseMethodMessage(long id, String method) {
        super(id);
        this.method = method;
    }
}
