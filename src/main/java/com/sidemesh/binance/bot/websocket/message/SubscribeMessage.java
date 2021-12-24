package com.sidemesh.binance.bot.websocket.message;

import com.sidemesh.binance.bot.Symbol;

import java.util.Set;
import java.util.stream.Collectors;

public class SubscribeMessage extends BaseMethodMessage {

    public final Set<String> params;

    public SubscribeMessage(Set<Symbol> symbols, long messageId) {
        super(messageId, "SUBSCRIBE");
        // 注意必须要小写
        // 订阅实时交易
        this.params = symbols.stream().map(it -> it.toLowerCase() + "@trade").collect(Collectors.toSet());
        // 订阅最优下单价格
        this.params.addAll(symbols.stream().map(it -> it.toLowerCase() + "@bookTicker").collect(Collectors.toSet()));
    }

}
