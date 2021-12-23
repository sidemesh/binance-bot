package com.sidemesh.binance.bot.log;

import com.google.api.client.util.Lists;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.grid.Grid;
import com.sidemesh.binance.bot.util.Convert;

import java.util.Collection;
import java.util.List;
import java.util.Map;

class Logs {

    public static class Bot implements Convert.ToMap {

        public final String id;
        public final String name;
        public final Symbol symbol;
        public final Long createdAt;

        public Bot(String id, String name, Symbol symbol, Long createdAt) {
            this.id = id;
            this.name = name;
            this.symbol = symbol;
            this.createdAt = createdAt;
        }

    }

    public static class GridBot extends Bot implements Convert.ToMap {

        public final List<Grid> grids;

        public GridBot(String id, String name, Symbol symbol, Long createdAt, List<Grid> grids) {
            super(id, name, symbol, createdAt);
            this.grids = grids;
        }

        public static GridBot of(com.sidemesh.binance.bot.GridBot b) {
            return new GridBot(b.id(), b.name(), b.symbol(), b.createdAt().getTime(), b.grids());
        }

        @Override
        public Map<String, Object> toMap() {
            var map = super.toMap();
            final Collection<Map<String, Object>> list = Lists.newArrayList();
            grids.forEach(it -> list.add(it.toMap()));
            map.put("girds", list);

            return map;
        }
    }


}