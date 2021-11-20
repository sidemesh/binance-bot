package com.slidemesh.binance.bot;

/**
 * 下单接口
 */
public interface Order {

    /**
     * 获取订单的请求信息
     * @return 订单请求信息
     */
    OrderRequest getRequest();

    /**
     * 获取订单的结果信息
     * @return 可选的结果信息
     */
    OrderResponse getResponse();

}
