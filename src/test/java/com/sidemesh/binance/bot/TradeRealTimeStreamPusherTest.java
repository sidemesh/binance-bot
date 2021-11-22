package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.grid.FixedBoundTradeGridBuilder;
import com.sidemesh.binance.bot.grid.TradeGrid;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TradeRealTimeStreamPusherTest {

    @Test
    void update() {
        var symbol = Symbol.BNB_USDT;
        var tradeGrid = TradeGrid.generate(BigDecimal.valueOf(100),
                new FixedBoundTradeGridBuilder(BigDecimal.ONE, BigDecimal.TEN, 10));
        var posit = BigDecimal.ZERO;
        var account = new Account("key", "secret");

        SimpleGridBot aBot = new SimpleGridBot("a bot", symbol, null, tradeGrid, account, posit);
        aBot.run();
        TradeRealTimeStreamPusher pusher = new TradeRealTimeStreamPusher();
        pusher.registry(aBot);

        RealtimeStreamData realtimeStreamData = new RealtimeStreamData(symbol);
        pusher.update(realtimeStreamData);
    }
}