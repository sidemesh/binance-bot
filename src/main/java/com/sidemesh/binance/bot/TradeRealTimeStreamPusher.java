package com.sidemesh.binance.bot;

import java.util.HashSet;
import java.util.Set;

public class TradeRealTimeStreamPusher implements RealtimeStreamListener {

    private final Set<Bot> BOT_LISTENERS = new HashSet<>();

    /**
     * 注册机器人
     *
     * @param bot
     */
    public void registry(Bot bot) {
        BOT_LISTENERS.add(bot);
    }

    @Override
    public void update(RealtimeStreamData data) {
    }
}
