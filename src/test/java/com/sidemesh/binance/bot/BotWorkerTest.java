package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.worker.BlockingQueueBotWorker;
import com.sidemesh.binance.bot.worker.BotWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BotWorkerTest {

    @Test
    public void test() throws InterruptedException {
        BotWorker w = new BlockingQueueBotWorker("test");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        var isSubmit = w.submit(() -> {
            try {
                Thread.sleep(300L);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assertions.assertTrue(isSubmit);

        var notImportant = w.submit(() -> {
            System.out.println("hello");
        });

        Assertions.assertFalse(notImportant);

        countDownLatch.await(10, TimeUnit.SECONDS);

        w.stop();
    }

}
