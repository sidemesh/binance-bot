package com.sidemesh.binance.bot.backtest;

import com.opencsv.exceptions.CsvValidationException;
import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RealtimeStreamBackTestDataImpl implements RealtimeStream {

    private final DataLoader dataLoader;
    private final List<RealtimeStreamListener> listeners;

    private volatile Thread thread;

    public RealtimeStreamBackTestDataImpl(Symbol symbol, String month) {
        dataLoader = new DataLoader(symbol, month);
        listeners = new ArrayList<>();
    }

    @Override
    public void start() {
        thread = new Thread(() -> {
            try {
                dataLoader.load(t -> {
                    busySleep(60000);
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

    @Override
    public void removeListener(Symbol symbol, RealtimeStreamListener listener) {
        // ignore
    }

    private void busySleep(long nanos)
    {
        long elapsed;
        final long startTime = System.nanoTime();
        do {
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < nanos);
    }
}
