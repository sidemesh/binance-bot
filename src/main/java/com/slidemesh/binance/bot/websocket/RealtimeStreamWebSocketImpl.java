package com.slidemesh.binance.bot.websocket;

import com.google.common.collect.Sets;
import com.slidemesh.binance.bot.RealtimeStream;
import com.slidemesh.binance.bot.RealtimeStreamListener;
import com.slidemesh.binance.bot.Symbol;
import com.slidemesh.binance.bot.websocket.message.SubscribeMessage;
import com.slidemesh.binance.bot.proxy.ProxyInfo;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class RealtimeStreamWebSocketImpl implements RealtimeStream {

    private static final Logger log = LoggerFactory.getLogger(RealtimeStreamWebSocketImpl.class);

    private static final String BINANCE_WSS = "wss://stream.binance.com:9443/ws";

    // 代理信息
    private final ProxyInfo proxyInfo;
    // 监听者
    private final Map<Symbol, List<RealtimeStreamListener>> symbolListenersMap = new HashMap<>();
    // close flag
    private boolean isClosed;
    // 当前 ws 链接
    private BinanceWebSocketClient bwsc;

    public RealtimeStreamWebSocketImpl(ProxyInfo proxyInfo) {
        this.proxyInfo = proxyInfo;
        symbolListenersMap.put(Symbol.LINK_USDT, null);
    }

    public RealtimeStreamWebSocketImpl() {
        this(null);
    }

    @Override
    public void run() {
        connect();
    }

    @Override
    public void stop() {
        close();
    }

    public synchronized void close() {
        if (!isClosed) {
            bwsc.close();
            isClosed = true;
        }
    }

    public synchronized BinanceWebSocketClient connect() {
        return bwsc = new BinanceWebSocketClient(
                proxyInfo,
                BINANCE_WSS,
                // on connect
                (client) -> {
                    client.subscribe(symbolListenersMap.keySet());
                },
                // on message
                (msg) -> {
                    log.info("on message: {}", msg);
                },
                // on close
                (reason) -> {
                    this.reconnect();
                },
                (e) -> {
                    /*
                        不应该无限重连，需要设置最大重连次数，超过最大次数发送报警信息
                     */
                    log.error("socket error", e);
                    this.reconnect();
                }
        );
    }

    private void reconnect() {
        if (!this.isClosed) {
            var client = connect();
            log.info("new websocket {} reconnect success!", client.id);
        }
    }

    @Override
    public synchronized void addListener(Symbol symbol, RealtimeStreamListener listener) {
        // 如果不存在 KEY 则需要订阅
        final var isNeedSubscribe = !symbolListenersMap.containsKey(symbol);

        final var list = symbolListenersMap.computeIfAbsent(symbol, (k) -> {
            // 新增监听
            return new ArrayList<>();
        });
        list.add(listener);
        symbolListenersMap.put(symbol, list);

        // 订阅 symbol
        if (isNeedSubscribe) {
            bwsc.subscribe(symbol);
        }
    }

    /**
     * web socket 客户端
     */
    private static class BinanceWebSocketClient extends okhttp3.WebSocketListener {
        private static final Logger log = LoggerFactory.getLogger(BinanceWebSocketClient.class);

        public final String id;
        // 自增的 message id
        private final AtomicLong messageId;
        // ws 实例
        private final okhttp3.WebSocket ws;

        // 回调函数
        private final Consumer<BinanceWebSocketClient> onConnected;
        private final Consumer<String> onMessage;
        private final Consumer<String> onClose;
        private final Consumer<Throwable> onError;

        public BinanceWebSocketClient(ProxyInfo proxyInfo,
                                      String url,
                                      Consumer<BinanceWebSocketClient> onConnected,
                                      Consumer<String> onMessage,
                                      Consumer<String> onClose,
                                      Consumer<Throwable> onError
                                      ) {
            // 随机一个UUID作为当前客户端唯一标识
            this.id = UUID.randomUUID().toString();
            // 原子自增的消息ID
            this.messageId= new AtomicLong(0);

            // 必须要在之前进行初始
            this.onClose = onClose;
            this.onConnected = onConnected;
            this.onMessage = onMessage;
            this.onError = onError;

            // build web socket
            var httpClientBuilder =
                    new OkHttpClient.Builder()
                            .connectTimeout(Duration.ofSeconds(10))
                            .pingInterval(Duration.ofSeconds(1))
                            // 可选的代理
                            .proxy(null != proxyInfo ? proxyInfo.toProxy() : null);

            var request = new Request.Builder()
                    .url(url + "/" + id)
                    .build();

            ws = httpClientBuilder
                    .build()
                    .newWebSocket(request, this);
        }

        public void subscribe(Symbol symbol) {
            subscribe(Sets.immutableEnumSet(symbol));
        }

        public void subscribe(Set<Symbol> symbols) {
            log.info("subscribe symbols {}", symbols);
            var msg = new SubscribeMessage(symbols, messageId.addAndGet(1L));
            ws.send(msg.toJson());
        }

        /**
         * 关闭连接
         */
        public void close() {
            ws.close(200, "manual close");
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            // 当连接打开执行回调
            onConnected.accept(this);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            onMessage.accept(text);
        }

        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            onClose.accept(String.format("code: %d, reason: %s", code, reason));
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            onError.accept(t);
        }
    }

}
