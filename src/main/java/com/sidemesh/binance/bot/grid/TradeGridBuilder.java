package com.sidemesh.binance.bot.grid;

import java.util.List;

public interface TradeGridBuilder {
    /**
     * 生成交易网格
     *
     * @return 交易网格
     */
    List<Grid> create();
}
