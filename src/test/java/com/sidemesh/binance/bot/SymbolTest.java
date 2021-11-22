package com.sidemesh.binance.bot;

import org.junit.jupiter.api.Test;

public class SymbolTest {

    @Test
    public void testValueOf() {
        Symbol.valueOf("ETH_USDT");
        Symbol.valueOf("ETHUSDT");
    }

}
