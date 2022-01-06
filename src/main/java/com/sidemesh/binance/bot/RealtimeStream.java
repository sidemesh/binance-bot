package com.sidemesh.binance.bot;

public interface RealtimeStream {
    void start();
    void stop();
    void addListener(Symbol symbol, RealtimeStreamListener listener);
    void removeListener(Symbol symbol, RealtimeStreamListener listener);
}
