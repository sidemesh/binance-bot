package com.sidemesh.binance.bot.websocket.message;

import com.google.common.collect.Sets;
import com.sidemesh.binance.bot.Symbol;

import java.util.Set;

public class SubscribeMessage extends BaseMethodMessage {

    public final Set<String> params;

    public SubscribeMessage(Set<Symbol> symbols, long messageId) {
        super(messageId, "SUBSCRIBE");
        final var set = Sets.<String>newHashSet();
        // 注意必须要小写
        // 订阅实时交易
        // 订阅最优下单价格
        symbols.forEach(it -> {
            set.add(it.toLowerCase() + "@trade");
            set.add(it.toLowerCase() + "@bookTicker");
        });
        this.params = set;
    }

}
