package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;
import com.fasterxml.jackson.annotation.JsonValue;

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

    public final String name;
    public final String baseAsset;
    public final String quoteAsset;
    public final BigDecimal pricePrecision;
    public final BigDecimal quantityPrecision;

    Symbol(String baseAsset, String quoteAsset, String precision, String quantity) {
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.name = baseAsset + quoteAsset;
        this.pricePrecision = new BigDecimal(precision);
        this.quantityPrecision = new BigDecimal(quantity);
    }

    public String lowerCaseName() {
        return name.toLowerCase();
    }

    public String upperCaseName() {
        return name.toUpperCase();
    }

    @Override
    public String getStr() {
        return name;
    }

    @Override
    public Symbol[] getValues() {
        return values();
    }

}
