package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.json.JSON;

/**
 * bot 元数据
 */
public interface BotMeta extends JSON{

    /**
     * 机器人名称
     */
    String getName();

    /**
     * 从Bot元数据 实例化一个Bot
     *
     * @param binanceAPI
     * @param realtimeStream
     * @return
     */
    Bot createBotFrom(BinanceAPI binanceAPI, RealtimeStream realtimeStream);
}
