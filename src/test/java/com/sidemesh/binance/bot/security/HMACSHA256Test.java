package com.sidemesh.binance.bot.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HMACSHA256Test {

    @Test
    public void testSign() {
        String message = "hello world!";
        String secret = "pass";

        assertNotNull(HMACSHA256.sign(message, secret));
    }

}
