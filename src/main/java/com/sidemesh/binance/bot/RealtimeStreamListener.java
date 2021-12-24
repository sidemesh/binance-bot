package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.websocket.event.BookTickerMessage;

public interface RealtimeStreamListener {
    // 当交易对存在新的成交
    void update(RealtimeStreamData data);
    // 当盯盘蒲更新
    default void update(BookTickerMessage msg) {
        // Do nothing
    }

}
