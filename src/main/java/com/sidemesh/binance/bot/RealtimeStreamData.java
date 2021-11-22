package com.sidemesh.binance.bot;

import java.math.BigDecimal;

public interface RealtimeStreamData {

    /**
     * 递增的 message Id
     */
    long id();
    BigDecimal price();

}
