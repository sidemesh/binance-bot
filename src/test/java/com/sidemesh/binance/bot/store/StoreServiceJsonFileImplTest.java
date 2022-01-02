package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.BotMeta;
import com.sidemesh.binance.bot.SimpleGridBot;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.backtest.FakeBinanceAPI;
import com.sidemesh.binance.bot.backtest.RealtimeStreamBackTestDataImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

class StoreServiceJsonFileImplTest {
    private final StoreService storeService = new StoreServiceJsonFileImpl();

    @Test
    void canCreateJsonFileWhenSaveBot() throws InterruptedException {
        String botName = "test-01";
        // 删除文件
        storeService.deleteBot(botName);

        var realtime = new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05");
        new SimpleGridBot(
                botName,
                Symbol.REN_USDT,
                Account.of("1", "1"),
                new FakeBinanceAPI(),
                new BigDecimal(1010),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                80,
                realtime
        );
        // 等待文件 异步创建完成
        TimeUnit.SECONDS.sleep(1);
        List<BotMeta> list = storeService.getList();
        boolean b = list.stream().anyMatch(v -> botName.equals(v.getName()));
        Assertions.assertTrue(b);
    }
}