package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.SimpleGridBot;
import com.sidemesh.binance.bot.Symbol;

import java.math.BigDecimal;

/**
 * 回测程序
 */
public class BackTest {

    public static void main(String[] args) throws InterruptedException {
        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05");
        var bot = new SimpleGridBot(
                "test-01",
                Symbol.REN_USDT,
                Account.of("1", "1"),
                new FakeBinanceAPI(),
                new BigDecimal(1010),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                100,
                realtime
        );
        bot.run();
        realtime.run();
    }

}
