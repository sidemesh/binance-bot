package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.grid.Grid;

import java.util.List;

public interface GridBot extends Bot {

    List<Grid> grids();

}
