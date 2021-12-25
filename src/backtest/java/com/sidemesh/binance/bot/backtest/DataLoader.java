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
import java.util.function.Consumer;

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

    public void load(Consumer<Trade> consumer) throws IOException, CsvValidationException {
        if (!isDownloaded()) {
            Downloader.monthly(symbol, month, BASE_PATH);
        }

        // read csv
        var reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8);
        var csvReader = new CSVReader(reader);

        csvReader.forEach(it -> consumer.accept(toTrade(it)));
    }

    private Trade toTrade(String[] line) {
        return new Trade(line[0], new BigDecimal(line[1]), Long.valueOf(line[4]));
    }

}
