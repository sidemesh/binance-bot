package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestDownloader {

    @Test
    public void testDownloader() throws IOException {
        Downloader.monthly(Symbol.REN_USDT, "2020-05", DataLoader.BASE_PATH);
    }

}
