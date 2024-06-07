package com.sidemesh.binance.bot.websocket.message;

import com.google.common.collect.Sets;
import com.sidemesh.binance.bot.Symbol;

import java.util.Set;

public class BaseSubscribeMessage extends BaseMethodMessage {

    public final Set<String> params;

    public BaseSubscribeMessage(String method, Set<Symbol> symbols, long messageId) {
        super(messageId, method);
        final var set = Sets.<String>newHashSet();
        // 注意必须要小写
        // 订阅实时交易
        // 订阅最优下单价格
        symbols.forEach(it -> {
            final var name = it.lowerCaseName();
            set.add(name+ "@trade");
            set.add(name+ "@bookTicker");
        });
        this.params = set;
    }
}
