package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("binance-bot\n");

        var proxy = new ClashProxy("127.0.0.1", 7890);
        var realtimeStream = new RealtimeStreamWebSocketImpl(proxy);

        realtimeStream.run();

        Thread.sleep(100000000000000000L);
    }

}
