package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.grid.FixedBoundTradeGridBuilder;
import com.sidemesh.binance.bot.grid.TradeGrid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TradeRealTimeStreamPusherTest {
    private static TradeRealTimeStreamPusher pusher;

    @BeforeAll
    public static void setUp() {
        var symbol = Symbol.BNB_USDT;
        var tradeGrid = TradeGrid.generate(BigDecimal.valueOf(100),
                new FixedBoundTradeGridBuilder(BigDecimal.ONE, BigDecimal.TEN, 10));
        var posit = BigDecimal.ZERO;
        var account = new Account("key", "secret");

        SimpleGridBot aBot = new SimpleGridBot("a bot", symbol, null, tradeGrid, account, posit);
        aBot.run();
        pusher = new TradeRealTimeStreamPusher();
        pusher.registry(aBot);
    }


    @Test
    void priceNotChangeWhenUpdate() {
        RealtimeStreamData realtimeStreamData = new RealtimeStreamData(Symbol.BNB_USDT);
        for (int i = 0; i < 1000; i++) {
            pusher.update(realtimeStreamData);
        }
    }
    @Test
    void priceChangeWhenUpdate() {
        // todo
//        RealtimeStreamData realtimeStreamData = new RealtimeStreamData(Symbol.BNB_USDT);
//        for (int i = 0; i < 1000; i++) {
//            pusher.update(realtimeStreamData);
//        }
    }
}