package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.log.Log;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BotHub {

    private static final Set<Bot> bots = new HashSet<>();

    public static final BotHub shared = new BotHub();

    private BotHub() {}

    public BotHub add(GridBot bot) {
        bots.add(bot);
        Log.shared.log(bot);
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
