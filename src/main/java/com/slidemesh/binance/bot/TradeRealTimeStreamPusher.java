package com.slidemesh.binance.bot;

import java.util.HashSet;
import java.util.Set;

public class TradeRealTimeStreamPusher implements RealtimeStreamListener{

    private static final Set<Bot> BOT_LISTENERS = new HashSet<>();

    /**
     * 注册机器人
     * @param bot
     */
    public static void registry(Bot bot) {
        BOT_LISTENERS.add(bot);
    }

    @Override
    public void update(RealtimeStreamData data) {
        // 最新交易发生 调用Bot
        for (Bot botListener : BOT_LISTENERS) {
            botListener.onPriceUpdate(data);
        }
    }
}
