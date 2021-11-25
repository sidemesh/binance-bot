package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.grid.FixedBoundTradeGridBuilder;
import com.sidemesh.binance.bot.grid.TradeGrid;

import java.math.BigDecimal;

/**
 * 回测程序
 */
public class BackTest {

    public static void main(String[] args) throws InterruptedException {
        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05");

        var grid = TradeGrid.generate(new BigDecimal(100),
                new FixedBoundTradeGridBuilder(new BigDecimal(1), new BigDecimal(100), 10));

        /*
        var bot = new SimpleGridBot(
                "test-01",
                Symbol.REN_USDT,
                new BigDecimal(1),
                grid,
                Account.of("1", "1"),
                new FakeBinanceAPI(),
                new BigDecimal(10),
                realtime
        );
        bot.run();
         */
        realtime.run();
    }

}
