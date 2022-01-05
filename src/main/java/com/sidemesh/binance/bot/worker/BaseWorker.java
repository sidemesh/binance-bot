package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class BaseWorker implements Runnable, BotWorker {

    public final String name;
    // 当前 worker 线程
    private volatile Thread t;
    // 当前 worker 线程ID
    private int tid = -1;
    // 当前 worker 状态
    protected volatile boolean isStop = true;

    public BaseWorker(String name) {
        this.name = name;
    }

    @Override
    public synchronized void stop() {
        if (isStop) {
            log.warn("bot worker {} already stopped!", name);
            return;
        }

        log.info("stop bot worker {}", name);
        this.isStop = true;
        if (t.isAlive()) {
            beforeDestroyThread();
            t.interrupt();
        }
    }

    @Override
    public synchronized void start() {
        if (isStop) {
            tid += 1;
            t = new Thread(this, name + "-" + tid);
            t.start();
            this.isStop = false;
        } else {
            log.warn("start bot worker: " + name + " already running");
        }
    }

    /**
     * 线程销毁前回调🪝
     */
    void beforeDestroyThread() {}
}
