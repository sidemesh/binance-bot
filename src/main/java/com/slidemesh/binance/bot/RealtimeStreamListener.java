package com.slidemesh.binance.bot;

public interface RealtimeStreamListener {
    // 当交易对存在新的成交
    void update(RealtimeStreamData data);
}
