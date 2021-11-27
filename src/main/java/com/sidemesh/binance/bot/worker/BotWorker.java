package com.sidemesh.binance.bot.worker;

public interface BotWorker {
    boolean submit(Runnable runnable);
    void destroy();
}
