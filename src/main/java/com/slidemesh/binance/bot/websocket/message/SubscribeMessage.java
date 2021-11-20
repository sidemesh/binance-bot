package com.slidemesh.binance.bot.websocket.message;

import com.slidemesh.binance.bot.Symbol;

import java.util.Set;
import java.util.stream.Collectors;

public class SubscribeMessage extends BaseMethodMessage {

    public final Set<String> params;

    public SubscribeMessage(Set<Symbol> symbols, long messageId) {
        super(messageId, "SUBSCRIBE");
        // 注意必须要小写
        this.params = symbols.stream().map(it -> it.toLowerCase() + "@trade").collect(Collectors.toSet());
    }

}
