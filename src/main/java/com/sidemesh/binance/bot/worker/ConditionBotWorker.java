package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionBotWorker extends BaseWorker {

    private final Lock consumerLock;
    private final Condition consumerLockCondition;
    private volatile Runnable task;

    public ConditionBotWorker(String name) {
        super(name);
        consumerLock = new ReentrantLock();
        consumerLockCondition = consumerLock.newCondition();
    }

    public boolean submit(Runnable runnable) {
        if (consumerLock.tryLock()) {
            try {
                // TODO is need check is processing??
                task = runnable;
                consumerLockCondition.signalAll();
                return true;
            } finally {
                consumerLock.unlock();
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (!isStop) {
            try {
                consumerLock.lock();
                consumerLockCondition.await();
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
            }
        }
    }

}
