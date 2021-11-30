package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionBotWorker implements Runnable, BotWorker {

    // 当 worker 被销毁后应该丢弃
    private volatile boolean isDestroy = false;
    // 线程间可见
    private volatile boolean isProcessing = false;

    private final Lock consumerLock;
    private final Condition consumerLockCondition;
    private volatile Runnable task;

    public ConditionBotWorker(String name) {
        consumerLock = new ReentrantLock();
        consumerLockCondition = consumerLock.newCondition();

        new Thread(this, name).start();
    }

    public boolean submit(Runnable runnable) {
        if (!isProcessing) {
            try {
                if (consumerLock.tryLock()) {
                    // TODO is need check is processing??
                    task = runnable;
                    consumerLockCondition.signalAll();
                    return true;
                }
            } finally {
                consumerLock.unlock();
            }
        }

        return false;
    }

    @Override
    public void run() {
        while (!isDestroy) {
            try {
                consumerLock.lock();
                consumerLockCondition.await();
                isProcessing = true;
                final var t = task;
                task = null;
                if (t != null) {
                    t.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // ignore
            } finally {
                consumerLock.unlock();
                isProcessing = false;
            }
        }
    }

    public void destroy() {
        isDestroy = true;
    }

}
