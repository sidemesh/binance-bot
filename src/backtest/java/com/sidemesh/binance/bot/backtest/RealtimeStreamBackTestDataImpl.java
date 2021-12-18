package com.sidemesh.binance.bot.backtest;

import com.opencsv.exceptions.CsvValidationException;
import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;

import java.io.IOException;
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
            try {
                dataLoader.load(t -> {
                    try {
                        Thread.sleep(0L, 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    listeners.forEach(l -> l.update(t));
                });
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
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
