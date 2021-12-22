package com.sidemesh.binance.bot.log;

import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.grid.Grid;

import java.util.List;

class Logs {

    public static class Bot {

        public final String name;
        public final Symbol symbol;
        public final Long createdAt;
        public final List<Grid> grids;

        public Bot(String name, Symbol symbol, Long createdAt, List<Grid> grids) {
            this.name = name;
            this.symbol = symbol;
            this.createdAt = createdAt;
            this.grids = grids;
        }

        public Bot(com.sidemesh.binance.bot.Bot b) {
            this(b.name(), b.symbol(), null, b.grids());
        }
    }


}