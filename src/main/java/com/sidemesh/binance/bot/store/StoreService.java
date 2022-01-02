package com.sidemesh.binance.bot.store;

import com.sidemesh.binance.bot.Bot;
import com.sidemesh.binance.bot.BotMeta;

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
    void saveBot(Bot bot);

    /**
     * 更新 如果存在
     * @param bot
     * @return
     */
    void updateBotIfExist(Bot bot);

    /**
     * 删除
     * @param botName
     */
    void deleteBot(String botName);

    /**
     * 根据名称获取
     * @param botName
     * @return
     */
    BotMeta getByName(String botName);

    /**
     * 获取列表
     * @return
     */
    List<BotMeta> getList();

}
