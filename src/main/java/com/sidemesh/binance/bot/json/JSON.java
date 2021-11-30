package com.sidemesh.binance.bot.json;

public interface JSON {
    JSON jackson = new JSONJacksonImpl();

    String toJson(Object o);

    <T> T read(String json, Class<T> clazz);

    <T> T read(byte[] bytes, Class<T> clazz);

    class ToJson {
        public String toJson() {
            return JSON.jackson.toJson(this);
        }
    }
}
