package com.sidemesh.binance.bot.websocket;

import com.google.common.collect.Sets;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.json.JSON;
import com.sidemesh.binance.bot.websocket.message.SubscribeMessage;
import com.sidemesh.binance.bot.websocket.message.UnsubscribeMessage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Proxy;
import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * web socket 客户端
 */
@Slf4j
class BinanceWebSocketClient extends okhttp3.WebSocketListener {
    private static final String BINANCE_WSS = "wss://stream.binance.com:9443/ws";

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

    public BinanceWebSocketClient(Proxy proxy,
                                  Consumer<BinanceWebSocketClient> onConnected,
                                  Consumer<String> onMessage,
                                  Consumer<String> onClose,
                                  Consumer<Throwable> onError) {
        // 随机一个UUID作为当前客户端唯一标识
        this.id = UUID.randomUUID().toString();
        // 原子自增的消息ID
        this.messageId = new AtomicLong(0);

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
                        .proxy(proxy);

        var request = new Request.Builder()
                .url(BINANCE_WSS + "/" + id)
                .build();

        ws = httpClientBuilder
                .build()
                .newWebSocket(request, this);
    }

    public void subscribe(Symbol symbol) {
        subscribe(Sets.immutableEnumSet(symbol));
    }

    public void subscribe(Set<Symbol> symbols) {
        if (!symbols.isEmpty()) {
            log.info("subscribe symbols {}", symbols);
            send(new SubscribeMessage(symbols, messageId.addAndGet(1L)));
        } else {
            log.info("skip subscribe empty symbols {}", symbols);
        }
    }

    public void unsubscribe(Symbol symbol) {
        unsubscribe(Sets.immutableEnumSet(symbol));
    }

    public void unsubscribe(Set<Symbol> symbols) {
        if (!symbols.isEmpty()) {
            log.info("unsubscribe symbols {}", symbols);
            send(new UnsubscribeMessage(symbols, messageId.addAndGet(1L)));
        } else {
            log.info("skip unsubscribe empty symbols {}", symbols);
        }
    }

    private void send(JSON.ToJson message) {
        ws.send(message.toJson());
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
