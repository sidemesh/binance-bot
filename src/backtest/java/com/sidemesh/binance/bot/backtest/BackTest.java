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
        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.SHIB_USDT, "2021-11");
        var bot = new SimpleGridBot(
                "test-01",
                Symbol.SHIB_USDT,
                Account.of("1", "1"),
                new FakeBinanceAPI(),
                new BigDecimal(510),
                new BigDecimal("0.00007710"),
                new BigDecimal("0.00003410"),
                50,
                realtime
        );
        bot.run();
        realtime.run();
    }

}
