package com.sidemesh.binance.bot;

abstract class BaseBot {

    // 机器人名称
    public final String name;
    // 交易对
    public final Symbol symbol;

    public BaseBot(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String name() {
        return this.name;
    }

    public Symbol symbol() {
        return this.symbol;
    }

}
