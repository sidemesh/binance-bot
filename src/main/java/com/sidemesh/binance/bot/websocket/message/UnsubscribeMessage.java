package com.sidemesh.binance.bot.websocket.message;

import com.sidemesh.binance.bot.Symbol;

import java.util.Set;

public class UnsubscribeMessage extends BaseSubscribeMessage {

    public UnsubscribeMessage(Set<Symbol> symbols, long messageId) {
        super("UNSUBSCRIBE", symbols, messageId);
    }
}
