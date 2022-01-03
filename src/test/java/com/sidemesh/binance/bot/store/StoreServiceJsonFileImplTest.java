package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.*;
import com.sidemesh.binance.bot.backtest.FakeBinanceAPI;
import com.sidemesh.binance.bot.backtest.RealtimeStreamBackTestDataImpl;
import com.sidemesh.binance.bot.json.JSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

class StoreServiceJsonFileImplTest {
    private final StoreService storeService = new StoreServiceJsonFileImpl();

    @Test
    void canCreateJsonFileWhenSaveBot() throws InterruptedException {
        String botName = "test-01";
        // 删除文件
        storeService.delete(botName);

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
        List<BotStat> list = storeService.list();
        boolean b = list.stream().anyMatch(v -> botName.equals(v.getName()));
        Assertions.assertTrue(b);
        // 删除文件
        storeService.delete(botName);
    }

    @Test
    void canLoadFile() {
        String json = "{\"name\":\"test-01\",\"symbol\":\"RENUSDT\",\"status\":null,\"low\":0.0500,\"high\":0.1000,\"grids\":80,\"order\":0,\"invest\":1010,\"surplusInvest\":1010,\"positQuantity\":0,\"incomeTotal\":0,\"dealGridList\":[]}";
        BotStat botStat = JSON.jackson.read(json, BotStat.class);

        SimpleGridBot bot = new SimpleGridBot(botStat, new FakeBinanceAPI(), Account.of("1", "1"), new RealtimeStreamBackTestDataImpl(Symbol.REN_USDT, "2020-05"));
        Assertions.assertNotNull(bot);
    }

    @Test
    void canDelete() throws IOException, InterruptedException {
        String botName = "test-01";
        File file = new File(StoreServiceJsonFileImpl.BASE_PATH + botName + StoreServiceJsonFileImpl.BOT_STAT_SUFFIX);
        file.createNewFile();
        // 删除文件
        storeService.delete(botName);
        TimeUnit.SECONDS.sleep(1);
        Assertions.assertFalse(file.exists());
    }
}