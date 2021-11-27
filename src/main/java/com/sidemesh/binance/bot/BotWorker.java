package com.sidemesh.binance.bot;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BotWorker implements Runnable {

    // 当 worker 被销毁后应该丢弃
    private volatile boolean isDestroy = false;
    // 线程间可见
    private volatile boolean isProcessing = false;
    // 并发队列
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public BotWorker(String name) {
        new Thread(this, name).start();
    }

    public boolean submit(Runnable runnable, boolean isImportant) {
        if (isImportant) {
            tasks.add(runnable);
            return true;
        }

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
            } catch (Exception e) {
                e.printStackTrace();
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
