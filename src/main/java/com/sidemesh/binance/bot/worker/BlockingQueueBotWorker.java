package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BlockingQueueBotWorker implements Runnable, BotWorker {

    // 当 worker 被销毁后应该丢弃
    private volatile boolean isDestroy = false;
    // 线程间可见
    private volatile boolean isProcessing = false;
    // 并发队列
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public BlockingQueueBotWorker(String name) {
        new Thread(this, name).start();
    }

    public boolean submit(Runnable runnable) {
        if (!isProcessing) {
            // TODO 注意此处可能有大量并发
            tasks.add(runnable);
            return true;
        }

        return false;
    }

    @Override
    public void run() {
        while (!isDestroy) {
            try {
                var task = tasks.take();
                isProcessing = true;
                task.run();
            } catch (InterruptedException e) {
                // ignore
            } finally {
                isProcessing = false;
            }
        }
    }

    public void destroy() {
        isDestroy = true;
    }

}