package com.sidemesh.binance.bot.backtest;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sidemesh.binance.bot.Symbol;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public final static String BASE_PATH = System.getProperty("user.dir") + "/.binance-bot/data/backtest";

    public final Symbol symbol;
    public final String month;
    public final String filePath;

    public DataLoader(Symbol symbol, String month) {
        this.symbol = symbol;
        this.month = month;
        this.filePath = BASE_PATH + "/" + String.format("%s-trades-%s.csv", symbol.toUpperCaseStr(), month);
    }

    public boolean isDownloaded() {
        return new File(filePath).exists();
    }

    public List<Trade> load() throws IOException, CsvValidationException {
        if (!isDownloaded()) {
            Downloader.monthly(symbol, month, BASE_PATH);
        }

        // read csv
        var reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8);
        var csvReader = new CSVReader(reader);

        final List<Trade> trades = new ArrayList<>();
        csvReader.forEach(it -> trades.add(toTrade(it)));
        return trades;
    }

    private Trade toTrade(String[] line) {
        return new Trade(line[0], new BigDecimal(line[2]), Long.valueOf(line[4]));
    }

}
