package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;

public enum Symbol implements StringEnum<Symbol> {

    LINK_USDT("LINKUSDT"),
    ETH_USDT("ETHUSDT"),
    BNB_USDT("BNBUSDT"),
    REN_USDT("RENUSDT"),
    BTC_USDT("BTCUSDT");

    public final String STR;

    Symbol(String str) {
        this.STR = str;
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
