package com.slidemesh.binance.bot.order;

import com.slidemesh.binance.bot.Order;
import com.slidemesh.binance.bot.OrderRequest;
import com.slidemesh.binance.bot.OrderResponse;

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
}
