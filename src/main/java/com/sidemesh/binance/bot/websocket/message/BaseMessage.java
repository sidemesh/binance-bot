package com.sidemesh.binance.bot.websocket.message;

import com.sidemesh.binance.bot.json.JSON;

public class BaseMessage extends JSON.ToJson {

    public final long id;

    public BaseMessage(long id) {
        this.id = id;
    }
}
