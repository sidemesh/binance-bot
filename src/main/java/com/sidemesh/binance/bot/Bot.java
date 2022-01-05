package com.sidemesh.binance.bot;

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
    void start();

    /**
     * 停止机器人
     */
    void stop();

    /**
     * 机器人状态
     */
    BotStatusEnum getBotStatus();

    /**
     * 机器人数据
     */
    BotStat getBotStat();
}
