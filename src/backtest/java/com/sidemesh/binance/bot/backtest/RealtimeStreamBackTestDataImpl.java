package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;

import java.util.ArrayList;
import java.util.List;

public class RealtimeStreamBackTestDataImpl implements RealtimeStream {

    private final DataLoader dataLoader;
    private final List<RealtimeStreamListener> listeners;

    public RealtimeStreamBackTestDataImpl(Symbol symbol, String month) {
        dataLoader = new DataLoader(symbol, month);
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        List<Trade> trades;
        try {
            trades = dataLoader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        trades.forEach(t -> {
            listeners.forEach(l -> l.update(t));
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public void addListener(Symbol symbol, RealtimeStreamListener listener) {
        listeners.add(listener);
    }
}
