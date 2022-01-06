package com.sidemesh.binance.bot.api;

import com.sidemesh.binance.bot.*;
import com.sidemesh.binance.bot.json.JSON;
import com.sidemesh.binance.bot.order.OrderImpl;
import com.sidemesh.binance.bot.security.HMACSHA256;
import com.sidemesh.binance.bot.util.FastApiSelector;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.Proxy;
import java.time.Duration;
import java.util.Objects;

/**
 * 币安 API v3 实现
 */
@Slf4j
public class BinanceAPIv3 implements BinanceAPI {

    private final static String[] BINANCE_APIS = {"https://api.binance.com", "https://api1.binance.com", "https://api2.binance.com", "https://api3.binance.com"};
    private final static RequestBody OKHTTP_EMPTY_REQUEST_BODY = RequestBody.create(new byte[0]);

    protected final OkHttpClient cli;
    protected final FastApiSelector fastApiSelector;
    protected volatile String baseApi;
    // 请求限流器
    private final RequestLimiter limiter = new RequestLimiter(5);

    public BinanceAPIv3(Proxy proxy, Duration timeout, Duration callTimeOut) {
        final var httpCli =
                new OkHttpClient.Builder().connectTimeout(timeout).proxy(proxy).callTimeout(callTimeOut).build();
        final var fas = new FastApiSelector(httpCli, BINANCE_APIS);

        this.cli = httpCli;
        this.fastApiSelector = fas;
        this.baseApi = BINANCE_APIS[0];
    }

    // 单例
    private static volatile BinanceAPIv3 INSTANCE;
    public static BinanceAPIv3 getInstance() {
        if (INSTANCE == null) {
            synchronized (BinanceAPIv3.class) {
                if (INSTANCE == null) {
                    final var timeout = Duration.ofSeconds(2);
                    INSTANCE = new BinanceAPIv3(ApplicationOptions.INSTANCE.getProxy(), timeout, timeout);
                }
            }
        }
        return INSTANCE;
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
        // 需要确认币安针对是账户或者IP限流或者账户+IP限流
        if (!limiter.acquire()) throw BinanceAPIException.limited();

        final var paramas = request.toUrlParams();
        // 为了性能仅采用 URL Params 模式填充数据，并不设置 request body
        final var req = new Request.Builder()
                .url(buildRequestUrl(api, signByHMACSHA256(account, paramas), paramas))
                .header("X-MBX-APIKEY", account.key)
                .post(OKHTTP_EMPTY_REQUEST_BODY)
                .build();

        log.debug("send order request = {}", req.url());

        // 请求返回
        okhttp3.Response executed;
        try {
            executed = cli.newCall(req).execute();
        } catch (Exception e) {
            throw BinanceAPIException.error(e);
        }

        // 错误预声明
        ResponseError err;
        try {
            final var bytes = Objects.requireNonNull(executed.body()).bytes();
            if (executed.isSuccessful()) {
                final var res = JSON.jackson.read(bytes, OrderResponse.class);
                return new OrderImpl(request, res);
            } else {
                err = JSON.jackson.read(bytes, ResponseError.class);
            }
        } catch (Exception e) {
            throw BinanceAPIException.error(e);
        }

        // 处理接口请求 status 非200错误
        throw err != null ? new BinanceAPIException(err) : BinanceAPIException.message("api call unknown error!");
    }

    private void setBaseApi(String api) {
        this.baseApi = api;
    }

    private String buildRequestUrl(String api, String signature, String params) {
        return baseApi +  api + "?" + params + "&signature=" + signature;
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
