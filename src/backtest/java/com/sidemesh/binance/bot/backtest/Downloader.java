package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Symbol;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Okio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

        log.info("Download url {}", url);

        final var req = new Request.Builder().url(url).get().build();

        Response execute = cli.newCall(req).execute();
        try (var sink = Okio.buffer(Okio.sink(file))) {
            sink.writeAll(Objects.requireNonNull(execute.body()).source());
        } catch (Exception e) {
            e.printStackTrace();
        }

        unzip(file, path);
        log.info("downloaded!!!");
    }

    private static void unzip(File file, String to) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                log.warn("unzip has folder! {}", entry.getName());
                continue;
            }
            log.info("Extracting: {}", entry.getName());
            int count;
            byte[] data = new byte[2048];
            // write the files to the disk
            BufferedOutputStream dest = new BufferedOutputStream(new FileOutputStream(to + "/" + entry.getName()));
            while ((count = zis.read(data)) != -1) dest.write(data, 0, count);
            dest.flush();
            dest.close();
            zis.closeEntry();
        }
    }
}
