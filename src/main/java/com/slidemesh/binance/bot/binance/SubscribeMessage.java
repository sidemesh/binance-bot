package com.slidemesh.binance.bot.binance;

import com.slidemesh.binance.bot.Symbol;

import java.util.Set;
import java.util.stream.Collectors;

public class SubscribeMessage extends BaseMethodMessage {

    public Set<String> params;

    public SubscribeMessage(Set<Symbol> symbols, long messageId) {
        this.method = "SUBSCRIBE";
        this.id = messageId;
        this.params = symbols.stream().map(it -> it.str + "@trade").collect(Collectors.toSet());
    }

}
