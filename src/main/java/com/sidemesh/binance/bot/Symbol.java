package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;

import java.math.BigDecimal;

public enum Symbol implements StringEnum<Symbol> {

    LINK_USDT("LINKUSDT", "0.0000001", "0.1"),
    NEAR_USDT("NEARUSDT", "0.0000001", "0.1"),
    ETH_USDT("ETHUSDT","0.0000001", "0.1"),
    BNB_USDT("BNBUSDT","0.0000001", "0.1"),
    REN_USDT("RENUSDT","0.0000001", "0.1"),
    GALA_USDT("GALAUSDT","0.00001", "1"),
    ANKR_USDT("ANKRUSDT","0.0000001", "0.1"),
    CHESS_USDT("CHESSUSDT","0.001", "0.1"),
    BTC_USDT("BTCUSDT", "0.0000001", "0.1"),
    SHIB_USDT("SHIBUSDT", "0.00000001", "1"),
    CAKE_USDT("CAKEUSDT", "0.01", "0.01"),
    CHZ_USDT("CHZUSDT", "0.0001", "1"),
    BICO_USDT("BICOUSDT", "0.001", "0.01"),
    ;

    //
    public final BigDecimal pricePrecision;
    public final BigDecimal quantityPrecision;
    public final String STR;

    Symbol(String str, String precision, String quantity) {
        this.STR = str;
        this.pricePrecision = new BigDecimal(precision);
        this.quantityPrecision = new BigDecimal(quantity);
    }

    public String toUpperCaseStr() {
        return this.STR;
    }

    public String toLowerCase() {
        return STR.toLowerCase();
    }

    @Override
    public String getStr() {
        return STR;
    }

    @Override
    public Symbol[] getValues() {
        return values();
    }

}
