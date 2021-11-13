package com.slidemesh.binance.bot;

public interface RealtimeStream {

    void addListener(Symbol symbol, RealtimeStreamListener listener);

}
