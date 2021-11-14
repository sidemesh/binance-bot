package com.slidemesh.binance.bot;

import com.google.common.collect.Sets;
import com.slidemesh.binance.bot.binance.SubscribeMessage;
import com.slidemesh.binance.bot.proxy.ProxyInfo;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class RealtimeStreamImpl implements RealtimeStream {

    private static final Logger log = LoggerFactory.getLogger(RealtimeStreamImpl.class);

    private static final String BINANCE_WSS = "wss://stream.binance.com:9443/ws";

    // 代理信息
    private final ProxyInfo proxyInfo;
    // 监听者
    private final Map<Symbol, List<RealtimeStreamListener>> symbolListenersMap = new HashMap<>();
    // close flag
    private boolean isClosed;
    // 当前 ws 链接
    private BinanceWebSocketClient wsClient;

    public RealtimeStreamImpl(ProxyInfo proxyInfo) {
        this.proxyInfo = proxyInfo;
        symbolListenersMap.put(Symbol.BTC_USDT, null);
    }

    public RealtimeStreamImpl() {
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
        if (!this.isClosed) {
            this.wsClient.close();
            this.isClosed = true;
        }
    }

    public synchronized void connect() {
        wsClient = new BinanceWebSocketClient(
                proxyInfo,
                BINANCE_WSS,
                // on close
                (reason) -> {
                    this.reconnect();
                    return null;
                },
                // on connect
                (client) -> {
                    client.subscribe(symbolListenersMap.keySet());
                    return null;
                }
        );
    }

    private void reconnect() {
        if (!this.isClosed) {
            connect();
        }
    }

    @Override
    public synchronized void addListener(Symbol symbol, RealtimeStreamListener listener) {
        var list = symbolListenersMap.computeIfAbsent(symbol, (k) -> {
            // 新增监听
            return new ArrayList<>();
        });
        list.add(listener);
        symbolListenersMap.put(symbol, list);

        wsClient.subscribe(symbol);
    }

    /**
     * 通过符号生成 wss 链接
     */
    @Deprecated
    private URI generateWssStreamsURLBySymbols() {
        // 当前所有符号
        final var symbolList = new ArrayList<String>();
        symbolListenersMap.forEach((k, v) -> symbolList.add(k.str));
        final var symbols = String.join("/", symbolList);

        final var uri = String.format("%s/stream?streams=%s", BINANCE_WSS, symbols);
        return URI.create(uri);
    }

    /**
     * web socket 客户端
     */
    private static class BinanceWebSocketClient extends okhttp3.WebSocketListener {

        private final String id;
        private final Function<String, Void> onClose;
        private final Function<BinanceWebSocketClient, Void> onConnected;
        private final AtomicLong messageId;
        // 由于异步回调，需设置为线程间可见
        private final okhttp3.WebSocket ws;

        public BinanceWebSocketClient(ProxyInfo proxyInfo,
                                      String url,
                                      Function<String, Void> onClose,
                                      Function<BinanceWebSocketClient, Void> onConnected) {
            this.id = UUID.randomUUID().toString();
            this.messageId= new AtomicLong(0);
            // 必须要在之前进行初始化
            this.onClose = onClose;
            this.onConnected = onConnected;

            var httpClientBuilder =
                    new OkHttpClient.Builder()
                            .connectTimeout(Duration.ofSeconds(10))
                            .pingInterval(Duration.ofSeconds(1));
            // 判断是否增加代理
            if (proxyInfo != null) {
                httpClientBuilder.proxy(proxyInfo.toProxy());
            }

            var request = new Request.Builder()
                    .url(url + "/" + this.id)
                    .build();

            // ws 配置
            ws = httpClientBuilder
                    .build()
                    .newWebSocket(request, this);
        }

        public void subscribe(Symbol symbol) {
            subscribe(Sets.immutableEnumSet(symbol));
        }

        public void subscribe(Set<Symbol> symbols) {
            var msg = new SubscribeMessage(symbols, messageId.addAndGet(1L));
            ws.send(msg.toJson());
        }

        public void close() {
            ws.close(200, "manual close");
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            onConnected.apply(this);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            log.info("ws {} on text: {}", id, text);
        }

        /*
        @Override
        public void onOpen(WebSocket webSocket) {
            log.info("ws {} connected!", id);
            ws = webSocket;
            onConnected.apply(this);
            WebSocket.Listener.super.onOpen(webSocket);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            log.info("ws {} on text: {}", id, data);
            return WebSocket.Listener.super.onText(webSocket, data, last);
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            onClose.apply(reason);
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            log.error("ws error: ", error);
            onClose.apply("error: " + error.getMessage());
        }
         */
    }

}
