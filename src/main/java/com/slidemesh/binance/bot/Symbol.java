package com.slidemesh.binance.bot;

import com.slidemesh.binance.bot.util.StringEnum;

public enum Symbol implements StringEnum<Symbol> {

    LINK_USDT("LINKUSDT"),
    ETH_USDT("ETHUSDT"),
    BNB_USDT("BNBUSDT"),
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
