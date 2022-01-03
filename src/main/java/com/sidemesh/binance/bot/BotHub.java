package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;

import java.util.*;
import java.util.stream.Collectors;

public class BotHub {

    private static final Map<String, Bot> botMap = new HashMap<>();
    private final StoreService storeService = new StoreServiceJsonFileImpl();

    public BotHub add(Bot bot) {
        if (get(bot.name()).isPresent()) {
            throw new IllegalArgumentException("bot exist botName=" + bot.name());
        }
        botMap.put(bot.name(), bot);
        return this;
    }

    public Collection<Bot> all() {
        return botMap.values();
    }

    public Optional<Bot> get(String name) {
        return Optional.ofNullable(botMap.get(name));
    }

    public Bot remove(String name) {
        return botMap.remove(name);
    }

    public Bot destroy(String name) {
        Bot bot = botMap.remove(name);
        if (bot != null) {
            storeService.deleteBot(name);
        }
        return bot;
    }

    public List<Bot> load(BinanceAPI binanceAPI, RealtimeStream rts) {
        List<Bot> loadBots = storeService.getList().stream()
                .map(meta -> meta.createBotFrom(binanceAPI, rts))
                .filter(bot -> get(bot.name()).isEmpty())
                .collect(Collectors.toList());
        for (Bot bot : loadBots) {
            add(bot);
        }
        return loadBots;
    }

}
