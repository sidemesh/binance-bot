package com.sidemesh.binance.bot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        get(name).ifPresent(Bot::stop);
        return botMap.remove(name);
    }

}
