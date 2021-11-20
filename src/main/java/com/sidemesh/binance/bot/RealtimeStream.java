package com.sidemesh.binance.bot;

public interface RealtimeStream {
    void run();
    void stop();
    void addListener(Symbol symbol, RealtimeStreamListener listener);
}
