package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;

import java.util.*;
import java.util.stream.Collectors;

public class BotHub {

    private static final Map<String, Bot> botMap = new HashMap<>();

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

}
