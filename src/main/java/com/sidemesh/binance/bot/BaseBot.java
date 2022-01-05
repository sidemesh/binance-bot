package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.worker.BotWorker;
import com.sidemesh.binance.bot.worker.ConditionBotWorker;

abstract class BaseBot implements Bot {

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

    protected BotWorker createConditionBotWorker() {
        final var workerName = String.format("%s-%s-worker", symbol.STR, name);
        return new ConditionBotWorker(workerName);
    }

}
