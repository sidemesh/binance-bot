package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.RealtimeStreamData;

import java.math.BigDecimal;

public class Trade implements RealtimeStreamData {

    public final String id;
    public final BigDecimal price;
    public final Long timestamp;

    public Trade(String id, BigDecimal price, Long timestamp) {
        this.id = id;
        this.price = price;
        this.timestamp = timestamp;
    }

    @Override
    public BigDecimal price() {
        return price;
    }
}
