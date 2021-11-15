package com.slidemesh.binance.bot.api;

import com.slidemesh.binance.bot.Account;
import com.slidemesh.binance.bot.Order;
import com.slidemesh.binance.bot.OrderException;
import com.slidemesh.binance.bot.util.FastApiSelector;
import okhttp3.OkHttpClient;

import java.math.BigDecimal;
import java.net.Proxy;
import java.time.Duration;

public class OrderHttpApiImpl implements Order {

    private final static String[] BINANCE_APIS = {
            "https://api.binance.com",
            "https://api1.binance.com",
            "https://api2.binance.com",
            "https://api3.binance.com"
    };

    private final OkHttpClient cli;
    private final FastApiSelector fastApiSelector;
    private volatile String baseApi;

    public OrderHttpApiImpl(Proxy proxy, Duration timeout, Duration callTimeOut) {
        final var httpCli =  new OkHttpClient.Builder().connectTimeout(timeout).proxy(proxy).callTimeout(callTimeOut).build();
        final var fas = new FastApiSelector(httpCli, BINANCE_APIS);

        this.cli = httpCli;
        this.fastApiSelector = fas;
        // 立刻获取一个最优地址
        this.baseApi = fas.once();
        this.fastApiSelector.onUpdate((h) -> this.baseApi = h).loop(10, 10);
    }

    public OrderHttpApiImpl() {
        this(null, Duration.ofSeconds(1), Duration.ofSeconds(1));
    }

    @Override
    public void buy(Account account, BigDecimal price, int amount) throws OrderException {

    }

    @Override
    public void sell(Account account, BigDecimal price, int amount) throws OrderException {

    }
}
