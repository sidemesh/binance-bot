package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Order;
import com.sidemesh.binance.bot.OrderRequest;
import com.sidemesh.binance.bot.OrderResponse;
import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;

public class FakeBinanceAPI implements BinanceAPI {

    @Override
    public Order order(Account account, OrderRequest request) throws BinanceAPIException {
        return new Order() {
            @Override
            public OrderRequest getRequest() {
                return request;
            }

            @Override
            public OrderResponse getResponse() {
                var res = new OrderResponse();
                res.setPrice(request.price);
                return res;
            }

            @Override
            public boolean isDeal() {
                return true;
            }
        };
    }

    @Override
    public Order orderTest(Account account, OrderRequest request) throws BinanceAPIException {
        return null;
    }
}
