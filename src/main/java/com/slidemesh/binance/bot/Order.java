package com.slidemesh.binance.bot;

import java.math.BigDecimal;

/**
 * 下单接口
 */
public interface Order {

    /**
     * 购买
     * @param account 账号
     * @param price 价格
     * @param amount 数量
     * @throws OrderException 异常
     */
    void buy(Account account, BigDecimal price, int amount) throws OrderException;

    /**
     * 卖出
     * @param account 账号
     * @param price 价格
     * @param amount 数量
     * @throws OrderException 异常
     */
    void sell(Account account, BigDecimal price, int amount) throws OrderException;

}
