package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.Bot;
import com.sidemesh.binance.bot.BotStat;
import com.sidemesh.binance.bot.json.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class StoreServiceJsonFileImpl implements StoreService {
    public static final String BASE_PATH = System.getProperty("user.dir") + "/.binance-bot/botdata/";
    public static final String BOT_STAT_SUFFIX = "-stat.json";

    private static final ExecutorService pool = Executors.newSingleThreadExecutor();

    @Override
    public void save(Bot bot) {
        pool.submit(() -> {
            BotStat botStat = bot.getBotStat();
            File botStatFile = getBotStatFile(botStat.getName());
            if (botStatFile.exists()) {
                throw new IllegalStateException("botFile had already created!");
            }
            try {
                createDirIfNotExist(botStatFile);
                botStat.setStatus(null); // 不持久化状态
                Files.writeString(botStatFile.toPath(), botStat.toJson(), StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                log.error("save bot to json file error [file={}, error={}]", botStatFile.getAbsolutePath(), e);
            }
        });
    }

    @Override
    public void update(Bot bot) {
        pool.submit(() -> {
            BotStat botStat = bot.getBotStat();
            File botStatFile = getBotStatFile(botStat.getName());
            // 如果bot存在
            if (botStatFile.exists()) {
                try {
                    botStat.setStatus(null); // 不持久化状态
                    // overwrite file
                    Files.writeString(botStatFile.toPath(), botStat.toJson());
                } catch (IOException e) {
                    log.error("update bot json file error [file={}, error={}]", botStatFile.getAbsolutePath(), e);
                }
            }
        });
    }

    @Override
    public void delete(String botName) {
        pool.submit(() -> {
            File botStatFile = getBotStatFile(botName);
            // 如果bot存在
            if (botStatFile.exists()) {
                log.info("delete bot json file [file={}]", botStatFile.getAbsolutePath());
                botStatFile.delete();
            }
        });
    }

    @Override
    public BotStat getByName(String botName) {
        File botStatFile = getBotStatFile(botName);
        // 如果bot存在
        if (botStatFile.exists()) {
            try {
                return JSON.jackson.read(Files.readString(botStatFile.toPath()),
                        BotStat.class);
            } catch (IOException e) {
                log.info("get bot json file error [file={}, error={}]", botStatFile.getAbsolutePath(), e);
            }
        }
        return null;
    }

    @Override
    public List<BotStat> list() {
        List<BotStat> list = new ArrayList<>();
        File dir = new File(BASE_PATH);
        try {
            Files.list(dir.toPath())
                    .filter(path -> path.toString().endsWith(BOT_STAT_SUFFIX))
                    .forEach(path -> {
                        try {
                            list.add(JSON.jackson.read(Files.readString(path), BotStat.class));
                        } catch (IOException e) {
                            log.info("list bot json file error [file={}, error={}]", path, e);
                        }
                    });
        } catch (IOException e) {
            log.error("read all bot json file error [basePath={}, error={}]", BASE_PATH, e);
        }
        return list;
    }

    private void createDirIfNotExist(File botStatFile) throws IOException {
        if (!botStatFile.getParentFile().exists()) {
            if (!botStatFile.getParentFile().mkdirs()) {
                throw new IOException("file dir create fail");
            }
        }
    }

    private File getBotStatFile(String botName) {
        return new File(BASE_PATH + botName + BOT_STAT_SUFFIX);
    }
}
