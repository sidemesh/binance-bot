package com.sidemesh.binance.bot;

import com.slidemesh.binance.bot.OrderRequest;
import com.slidemesh.binance.bot.Symbol;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderRequestTest {

    @Test
    public void testToURLParams() {
        final var price = BigDecimal.valueOf(100);
        final var quantity = BigDecimal.valueOf(10);
        final var symbol = Symbol.ETH_USDT;
        final var tz = new Date().getTime();
        final var rc = 1000L;
        final var id = "123";


        final var builder = OrderRequest.newLimitOrderBuilder();
        final var req = builder
                .buy()
                .id(id)
                .symbol(symbol)
                .price(price)
                .quantity(quantity)
                .timestamp(tz)
                .rcwindow(rc)
                .build();

        assertNotNull(req.toUrlParams());
    }

}
