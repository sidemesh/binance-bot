package com.sidemesh.binance.bot;

public interface RealtimeStream {
    void start();
    void stop();
    void addListener(Symbol symbol, RealtimeStreamListener listener);
    void unListen(Symbol symbol, RealtimeStreamListener listener);
}
