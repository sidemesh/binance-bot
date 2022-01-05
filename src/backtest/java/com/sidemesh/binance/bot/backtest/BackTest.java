package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Bot;
import com.sidemesh.binance.bot.SimpleGridBot;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 回测程序
 */
public class BackTest {

    public static void main(String[] args) {
        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05");

        StoreService storeService = new StoreServiceJsonFileImpl();
        List<Bot> bots = Optional.of(storeService.list())
                .filter(l -> l.size() > 0)
                // load from file
                .map(botStats -> {
                    List<Bot> list = botStats.stream()
                            .map(botStat -> new SimpleGridBot(botStat, new FakeBinanceAPI(), Account.of("1", "1"), realtime))
                            .collect(Collectors.toList());
                    return list;
                })
                // new bot
                .orElseGet(() -> {
                    var bot = new SimpleGridBot(
                            "RENUSDT-01",
                            Symbol.REN_USDT,
                            Account.of("1", "1"),
                            new FakeBinanceAPI(),
                            new BigDecimal(1010),
                            new BigDecimal("0.05"),
                            new BigDecimal("0.1"),
                            80,
                            realtime
                    );
                    return Collections.singletonList(bot);
                });

        bots.forEach(Bot::run);
        realtime.run();
    }

}
