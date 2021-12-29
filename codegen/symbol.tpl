package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;

import java.math.BigDecimal;

public enum Symbol implements StringEnum<Symbol> {

    // generate code from binance api
    // generate code from binance api
    // generate code from binance api
    {{ range . }}
    {{.ToJavaEnum}}
    {{end}}
    ;
    // generate code from binance api
    // generate code from binance api
    // generate code from binance api

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
