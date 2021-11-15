package com.slidemesh.binance.bot.json;

public interface JSON {
    JSON jackson = new JSONJacksonImpl();

    String toJson(Object o);

    class ToJson {
        public String toJson() {
            return JSON.jackson.toJson(this);
        }
    }
}
