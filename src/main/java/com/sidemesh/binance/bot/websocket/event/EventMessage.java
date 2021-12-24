package com.sidemesh.binance.bot.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessage {

    @JsonProperty("e")
    public String event;

    public boolean isTrade() {
        return "trade".equals(event);
    }
}
