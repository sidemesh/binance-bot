package com.sidemesh.binance.bot.api;

import com.sidemesh.binance.bot.Order;
import com.sidemesh.binance.bot.json.JSON;
import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.OrderRequest;
import com.sidemesh.binance.bot.OrderResponse;
import com.sidemesh.binance.bot.order.OrderImpl;
import com.sidemesh.binance.bot.security.HMACSHA256;
import com.sidemesh.binance.bot.util.FastApiSelector;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.Proxy;
import java.time.Duration;

/**
 * 币安 API v3 实现
 */
public class BinanceAPIv3 implements BinanceAPI {

    private final static String[] BINANCE_APIS = {"https://api.binance.com", "https://api1.binance.com", "https://api2.binance.com", "https://api3.binance.com"};
    private final static RequestBody OKHTTP_EMPTY_REQUEST_BODY = RequestBody.create(new byte[0]);

    private final RequestLimiter limiter = new RequestLimiter(5);
    protected final OkHttpClient cli;
    protected final FastApiSelector fastApiSelector;
    protected volatile String baseApi;

    public BinanceAPIv3(Proxy proxy, Duration timeout, Duration callTimeOut) {
        final var httpCli =  new OkHttpClient.Builder().connectTimeout(timeout).proxy(proxy).callTimeout(callTimeOut).build();
        final var fas = new FastApiSelector(httpCli, BINANCE_APIS);

        this.cli = httpCli;
        this.fastApiSelector = fas;
        // 立刻获取一个最优地址
        this.baseApi = fas.once();
        this.fastApiSelector.onUpdate(this::setBaseApi).loop(10, 10);
    }

    public BinanceAPIv3() {
        this(null, Duration.ofSeconds(1), Duration.ofSeconds(1));
    }

    /**
     * 正式的下单接口，支持购买和出售
     * @param account 交易的账户
     * @param request 请求信息
     * @return 下单结果
     * @throws BinanceAPIException 包装的异常信息
     */
    public Order order(Account account, OrderRequest request) throws BinanceAPIException {
        return order("/api/v3/order", account, request);
    }

    /**
     * 用于测试订单请求，但不会提交到撮合引擎，支持购买和出售.
     * @param account 交易的账户
     * @param request 请求信息
     * @return 下单结果
     * @throws BinanceAPIException 包装的异常信息
     */
    @Override
    public Order orderTest(Account account, OrderRequest request) throws BinanceAPIException {
        return order("/api/v3/order/test", account, request);
    }

    /**
     * 通用的下单接口
     */
    protected Order order(String api, Account account, OrderRequest request) throws BinanceAPIException {
        if (!limiter.acquire()) {
            throw new BinanceAPIException(true);
        }

        final var paramas = request.toUrlParams();

        // 为了性能仅采用 URL Params 模式填充数据
        final var req = new Request.Builder()
                .url(buildRequestUrl(api, signByHMACSHA256(account, paramas), paramas))
                .post(OKHTTP_EMPTY_REQUEST_BODY)
                .build();

        try {
            final var executed = cli.newCall(req).execute();
            final var response = JSON.jackson.read(executed.toString(), OrderResponse.class);
            return new OrderImpl(request, response);
        } catch (Exception e) {
            throw new BinanceAPIException(e);
        }
    }

    private void setBaseApi(String api) {
        this.baseApi = api;
    }

    private String buildRequestUrl(String api, String signature, String params) {
        return baseApi +  api + "?signature=" + signature + "&" + params;
    }

    /**
     * 使用 HMACSHA256 进行签名, account secret 作为秘钥。
     * @param account 账户
     * @param message 消息体
     * @return 加密字符串
     */
    private String signByHMACSHA256(Account account, String message) {
        return HMACSHA256.sign(message, account.secret);
    }
}
