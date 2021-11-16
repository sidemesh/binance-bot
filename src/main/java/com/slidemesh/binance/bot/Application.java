package com.slidemesh.binance.bot;

import com.slidemesh.binance.bot.proxy.ClashProxy;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("binance-bot\n\n\n");

        var proxy = new ClashProxy("127.0.0.1", 7890);
        var realtimeStream = new RealtimeStreamImpl(proxy);

        realtimeStream.run();

        Thread.sleep(100000000000000000L);
    }

}
