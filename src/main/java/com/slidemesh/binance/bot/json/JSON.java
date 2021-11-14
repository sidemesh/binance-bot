package com.slidemesh.binance.bot.json;

public interface JSON {

    String toJson(Object o);

    JSON jackson = new JSONJacksonImpl();

}
