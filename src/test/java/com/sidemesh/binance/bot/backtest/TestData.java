package com.sidemesh.binance.bot.backtest;

import com.opencsv.exceptions.CsvValidationException;
import com.sidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestData {

    @Test
    public void testLoad() throws CsvValidationException, IOException {

        var data = new DataLoader(Symbol.REN_USDT, "2020-05");

        var trades = data.load();
        Assertions.assertNotNull(trades);
        Assertions.assertTrue(trades.size() > 0);
    }

}
