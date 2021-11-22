package com.sidemesh.binance.bot.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestLimiterTest {


    @Test
    public void testAcquire() {
        var limiter = new RequestLimiter(1);

        Assertions.assertTrue(limiter.acquire());
        Assertions.assertFalse(limiter.acquire());
    }

}
