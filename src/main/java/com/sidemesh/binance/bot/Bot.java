package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.grid.Grid;

import java.util.List;

public interface Bot {

    /**
     * 机器人名称
     */
    String name();

    /**
     * 机器人交易的对
     * @return Symbol
     */
    Symbol symbol();

    /**
     * 启动机器人
     */
    void run();

    /**
     * 停止机器人
     */
    void stop();

    /**
     * 获取当前机器人网格
     */
    List<Grid> grids();

}
