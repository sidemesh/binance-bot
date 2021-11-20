package com.slidemesh.binance.bot.api;

import com.slidemesh.binance.bot.Account;
import com.slidemesh.binance.bot.Order;
import com.slidemesh.binance.bot.OrderRequest;

public interface BinanceAPI {

    Order order(Account account, OrderRequest request) throws BinanceAPIException;

}
