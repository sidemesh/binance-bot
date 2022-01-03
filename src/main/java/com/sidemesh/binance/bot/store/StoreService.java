package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.Bot;
import com.sidemesh.binance.bot.BotStat;

import java.util.List;

/**
 * 存储API
 */
public interface StoreService {

    /**
     * 保存
     * @param bot
     * @return
     */
    void save(Bot bot);

    /**
     * 更新
     * @param bot
     * @return
     */
    void update(Bot bot);

    /**
     * 删除
     * @param botName
     */
    void delete(String botName);

    /**
     * 根据名称获取
     * @param botName
     * @return
     */
    BotStat getByName(String botName);

    /**
     * 获取列表
     * @return
     */
    List<BotStat> list();

}
