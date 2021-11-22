package com.sidemesh.binance.bot.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sidemesh.binance.bot.RealtimeStreamData;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.json.JSON;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessage implements RealtimeStreamData {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("s")
    public Symbol symbol;

    @JsonProperty("result")
    public Object result;

    @JsonProperty("e")
    public String event;

    @JsonProperty("p")
    public BigDecimal price;

    @Override
    public BigDecimal price() {
        return price;
    }

    public boolean isTrade() {
        return "trade".equals(event);
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "id='" + id + '\'' +
                ", result=" + result +
                ", event=" + event +
                ", price=" + price +
                '}';
    }

    public static EventMessage ofJson(String json) {
        return JSON.jackson.read(json, EventMessage.class);
    }
}
