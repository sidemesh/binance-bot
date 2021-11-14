package com.slidemesh.binance.bot.json;

public class ToJson {

    public String toJson() {
        return JSON.jackson.toJson(this);
    }

}
