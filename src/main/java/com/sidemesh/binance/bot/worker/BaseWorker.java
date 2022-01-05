package com.sidemesh.binance.bot.worker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class BaseWorker implements Runnable, BotWorker {

    private volatile Thread t;
    protected volatile boolean isStop = true;
    public final String name;

    public BaseWorker(String name) {
        this.name = name;
    }

    @Override
    public void stop() {
        if (t.isAlive()) {
            t.interrupt();
        }
        this.isStop = true;
    }

    @Override
    public void start() {
        if (isStop) {
            t = new Thread(this, name);
            t.start();
            this.isStop = false;
        } else {
            log.warn("start bot worker: " + name + " already running");
        }
    }
}
