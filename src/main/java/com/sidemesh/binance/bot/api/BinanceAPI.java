package com.sidemesh.binance.bot.api;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Order;
import com.sidemesh.binance.bot.OrderRequest;

public interface BinanceAPI {

    Order order(Account account, OrderRequest request) throws BinanceAPIException;
    Order orderTest(Account account, OrderRequest request) throws BinanceAPIException;

}
