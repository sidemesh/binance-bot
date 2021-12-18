package com.sidemesh.binance.bot.order;

import com.sidemesh.binance.bot.Order;
import com.sidemesh.binance.bot.OrderRequest;
import com.sidemesh.binance.bot.OrderResponse;

public class OrderImpl implements Order {

    private final OrderRequest request;
    private final OrderResponse response;

    public OrderImpl(OrderRequest request, OrderResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public OrderRequest getRequest() {
        return this.request;
    }

    @Override
    public OrderResponse getResponse() {
        return this.response;
    }

    @Override
    public boolean isDeal() {
        return null != response && response.isSuccess();
    }
}
