package com.slidemesh.binance.bot.binance;

import com.slidemesh.binance.bot.json.JSON;

public class BaseMessage extends JSON.ToJson {

    public final long id;

    public BaseMessage(long id) {
        this.id = id;
    }
}
