package com.slidemesh.binance.bot;

public interface RealtimeStream {
    void run();
    void stop();
    void addListener(Symbol symbol, RealtimeStreamListener listener);
}
