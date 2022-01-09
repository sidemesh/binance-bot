package com.sidemesh.binance.bot.backtest;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Order;
import com.sidemesh.binance.bot.OrderRequest;
import com.sidemesh.binance.bot.OrderResponse;
import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.api.BinanceAPIException;

import java.util.Collections;

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
                res.setSymbol(getRequest().symbol);
                res.setPrice(request.price);
                res.setExecutedQty(request.quantity);
                res.setFills(Collections.emptyList());
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
