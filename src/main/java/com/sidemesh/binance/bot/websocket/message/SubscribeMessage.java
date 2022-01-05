package com.sidemesh.binance.bot.websocket.message;

import com.sidemesh.binance.bot.Symbol;

import java.util.Set;

public class SubscribeMessage extends BaseSubscribeMessage {

    public SubscribeMessage(Set<Symbol> symbols, long messageId) {
        super("SUBSCRIBE", symbols, messageId);
    }

}
