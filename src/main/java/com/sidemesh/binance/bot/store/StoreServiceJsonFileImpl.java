package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.Bot;
import com.sidemesh.binance.bot.BotMeta;
import com.sidemesh.binance.bot.SimpleGridBot;
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
    public final static String BASE_PATH = System.getProperty("user.dir") + "/.binance-bot/botdata/";
    private static final String BOT_INFO = "-info.json";

    private static final ExecutorService pool = Executors.newSingleThreadExecutor();

    @Override
    public void saveBot(Bot bot) {
        pool.submit(() -> {
            BotMeta botMeta = bot.botMeta();
            File botInfoFile = getBotInfoFile(botMeta.getName());
            if (botInfoFile.exists()) {
                throw new IllegalStateException("botFile had already created!");
            }
            try {
                createDirIfNotExist(botInfoFile);
                Files.writeString(botInfoFile.toPath(), botMeta.toJson(botMeta), StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                log.error("save bot to json file error [file={}, error={}]", botInfoFile.getAbsolutePath(), e);
            }
        });
    }

    @Override
    public void updateBotIfExist(Bot bot) {
        pool.submit(() -> {
            BotMeta botMeta = bot.botMeta();
            File botInfoFile = getBotInfoFile(botMeta.getName());
            // 如果bot存在
            if (botInfoFile.exists()) {
                try {
                    // overwrite file
                    Files.writeString(botInfoFile.toPath(), botMeta.toJson(botMeta));
                } catch (IOException e) {
                    log.error("update bot json file error [file={}, error={}]", botInfoFile.getAbsolutePath(), e);
                }
            }
        });
    }

    @Override
    public void deleteBot(String botName) {
        pool.submit(() -> {
            File botInfoFile = getBotInfoFile(botName);
            // 如果bot存在
            if (botInfoFile.exists()) {
                log.info("delete bot json file [file={}]", botInfoFile.getAbsolutePath());
                botInfoFile.delete();
            }
        });
    }

    @Override
    public BotMeta getByName(String botName) {
        File botInfoFile = getBotInfoFile(botName);
        // 如果bot存在
        if (botInfoFile.exists()) {
            try {
                return JSON.jackson.read(Files.readString(botInfoFile.toPath()),
                        SimpleGridBot.SimpleBotMeta.class);
            } catch (IOException e) {
                log.info("get bot json file error [file={}, error={}]", botInfoFile.getAbsolutePath(), e);
            }
        }
        return null;
    }

    @Override
    public List<BotMeta> getList() {
        List<BotMeta> list = new ArrayList<>();
        File dir = new File(BASE_PATH);
        try {
            Files.list(dir.toPath())
                    .filter(path -> path.toString().endsWith(BOT_INFO))
                    .forEach(path -> {
                        try {
                            // todo botMeta 使用哪个类实例化？
                            list.add(JSON.jackson.read(Files.readString(path), SimpleGridBot.SimpleBotMeta.class));
                        } catch (IOException e) {
                            log.info("list bot json file error [file={}, error={}]", path, e);
                        }
                    });
        } catch (IOException e) {
            log.error("read all bot json file error [basePath={}, error={}]", BASE_PATH, e);
        }
        return list;
    }

    private void createDirIfNotExist(File botInfoFile) throws IOException {
        if (!botInfoFile.getParentFile().exists()) {
            if (!botInfoFile.getParentFile().mkdirs()) {
                throw new IOException("file dir create fail");
            }
        }
    }

    private File getBotInfoFile(String botName) {
        return new File(BASE_PATH + botName + BOT_INFO);
    }
}
