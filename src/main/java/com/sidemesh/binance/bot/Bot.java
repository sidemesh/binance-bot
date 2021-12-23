package com.sidemesh.binance.bot;

import java.util.Date;

public interface Bot {

    String id();

    Date createdAt();

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

}
