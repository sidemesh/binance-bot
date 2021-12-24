package com.sidemesh.binance.bot.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sidemesh.binance.bot.RealtimeStreamData;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.json.JSON;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeMessage extends EventMessage implements RealtimeStreamData {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("s")
    public Symbol symbol;

    @JsonProperty("result")
    public Object result;

    @JsonProperty("p")
    public BigDecimal price;

    @JsonProperty("t")
    public Long tid;

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public long id() {
        return tid;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "id=" + id +
                ", symbol=" + symbol +
                ", result=" + result +
                ", event='" + event + '\'' +
                ", price=" + price +
                ", tid=" + tid +
                '}';
    }

    public static TradeMessage ofJson(String json) {
        return JSON.jackson.read(json, TradeMessage.class);
    }
}
