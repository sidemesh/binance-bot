package com.sidemesh.binance.bot;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BotHub {

    private static final Set<Bot> bots = new HashSet<>();

    public BotHub add(Bot bot) {
        bots.add(bot);
        return this;
    }

    public Collection<Bot> all() {
        return bots;
    }

    public Optional<Bot> get(String name) {
        for (var b : bots) {
            if (b.name().equals(name)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

}
