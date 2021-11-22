package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Symbol;

/**
 * 回测程序
 */
public class BackTest {

    public static void main(String[] args) throws InterruptedException {
        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05");
        realtime.addListener(Symbol.REN_USDT, System.out::println);
        realtime.run();
        Thread.sleep(100000000000L);
    }

}
