package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RealtimeStreamBackTestDataImpl implements RealtimeStream {

    private final DataLoader dataLoader;
    private final List<RealtimeStreamListener> listeners;

    private volatile Thread thread;

    public RealtimeStreamBackTestDataImpl(Symbol symbol, String month) {
        dataLoader = new DataLoader(symbol, month);
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        thread = new Thread(() -> {
            List<Trade> trades;
            try {
                trades = dataLoader.load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            trades.forEach(t -> {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t);
                listeners.forEach(l -> l.update(t));
            });

        },"rtl-test");

        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void addListener(Symbol symbol, RealtimeStreamListener listener) {
        listeners.add(listener);
    }
}
