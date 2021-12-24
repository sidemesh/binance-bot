package com.sidemesh.binance.bot.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.json.JSON;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookTickerMessage {

    // order book updateId
    @JsonProperty("u")
    public Long id;

    // 交易对
    @JsonProperty("s")
    public Symbol symbol;

    // 买单最优挂单价格
    @JsonProperty("b")
    public BigDecimal bestBuyPrice;

    // 买单最优挂单数量
    @JsonProperty("B")
    public BigDecimal bestBuyPriceAmount;

    // 卖单最优挂单数量
    @JsonProperty("a")
    public BigDecimal bestSellPrice;

    // 卖单最优挂单数量
    @JsonProperty("A")
    public BigDecimal bestSellPriceAmount;

    public static BookTickerMessage ofJson(String message) {
        return JSON.jackson.read(message, BookTickerMessage.class);
    }

    public boolean isBookTickerMessage() {
        return id != null && id > 0 && symbol != null && bestBuyPrice != null && bestSellPrice != null;
    }

    @Override
    public String toString() {
        return "BookTickerMessage{" +
                "id=" + id +
                ", symbol=" + symbol +
                ", bestBuyPrice=" + bestBuyPrice +
                ", bestBuyPriceAmount=" + bestBuyPriceAmount +
                ", bestSellPrice=" + bestSellPrice +
                ", bestSellPriceAmount=" + bestSellPriceAmount +
                '}';
    }
}
