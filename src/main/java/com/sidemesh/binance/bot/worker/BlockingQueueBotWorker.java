package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BlockingQueueBotWorker extends BaseWorker {

    // 线程间可见
    private volatile boolean isProcessing = false;
    // 并发队列
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public BlockingQueueBotWorker(String name) {
        super(name);
    }

    public boolean submit(Runnable runnable) {
        if (!isProcessing) {
            // TODO 注意此处可能有大量并发
            return tasks.add(runnable);
        }

        return false;
    }

    @Override
    public void run() {
        while (!isStop) {
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

}