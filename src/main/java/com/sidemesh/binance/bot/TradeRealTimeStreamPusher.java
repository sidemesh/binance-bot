package com.sidemesh.binance.bot;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class TradeRealTimeStreamPusher implements RealtimeStreamListener{

    private static final Set<Bot> BOT_LISTENERS = new HashSet<>();

    private static final ExecutorService pool;

    static {
        var builder = new ThreadFactoryBuilder();
        pool = new ThreadPoolExecutor(4, 20, 10, TimeUnit.SECONDS,
                // 阻塞队列为 10 极少数量消息会放入排队
                new ArrayBlockingQueue<>(10),
                builder.setNameFormat("rt-pusher-pool-%d").build(),
                // 超出阻塞队列 直接丢弃消息
                new ThreadPoolExecutor.DiscardPolicy());
    }

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
        for (Bot bot : BOT_LISTENERS) {
            if (bot.symbol() == data.symbol) {
                pool.submit(() -> bot.onPriceUpdate(data));
            }
        }
    }
}
