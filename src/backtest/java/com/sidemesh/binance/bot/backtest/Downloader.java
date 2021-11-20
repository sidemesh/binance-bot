package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
class Downloader {

    /**
     * 下载回测数据
     * @param month 月份
     * @param path 下载路径
     */
    public static void monthly(Symbol symbol, String month, String path) throws IOException {
        log.info("download back-test data month = {} path = {}", month, path);

        // create file dir
        Files.createDirectories(Paths.get(path));
        File file = new File(path, String.format("%s-%s.zip", symbol.toUpperCaseStr(), month));

        final var cli = new OkHttpClient.Builder().build();
        final var url = String.format("https://data.binance.vision/data/spot/monthly/trades/%s/%s-trades-%s.zip",
                symbol.toUpperCaseStr(), symbol.toUpperCaseStr(), month);
        final var req = new Request.Builder().url(url).get().build();

        Response execute = cli.newCall(req).execute();
        var sink = Okio.buffer(Okio.sink(file));
        sink.writeAll(execute.body().source());
        sink.close();
        unzip(file, path);
    }

    private static void unzip(File file, String to) throws ZipException {
        new ZipFile(file).extractAll(to);
    }
}
