package com.sidemesh.binance.bot.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestLimiterTest {

    @Test
    public void testAcquire() throws InterruptedException {
        var limiter = new RequestLimiter(5);
        for (var i = 0; i < 5; i ++) {
            Assertions.assertTrue(limiter.acquire());
        }
        Assertions.assertFalse(limiter.acquire());
        Thread.sleep(1000L);
        for (var i = 0; i < 5; i ++) {
            Assertions.assertTrue(limiter.acquire());
        }
    }

}
