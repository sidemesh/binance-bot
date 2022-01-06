package com.sidemesh.binance.bot.worker;

public interface BotWorker {
    /**
     * 运行一个任务
     * @param runnable 任务
     * @return 是否正在执行
     */
    boolean submit(Runnable runnable);

    /**
     * 可恢复的停止
     */
    void stop();

    /**
     * 创建新的线程并启动
     */
    void start();
}
