package com.sidemesh.binance.bot.websocket;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sidemesh.binance.bot.ApplicationOptions;
import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.websocket.event.BookTickerMessage;
import com.sidemesh.binance.bot.websocket.event.TradeMessage;
import lombok.extern.slf4j.Slf4j;

import java.net.Proxy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RealtimeStreamWebSocketImpl implements RealtimeStream {
    // 代理信息
    private final Proxy proxy;
    // 监听者
    private final Map<Symbol, List<RealtimeStreamListener>> symbolListenersMap = new HashMap<>();
    // close flag
    private boolean isClosed;
    // 当前 ws 链接
    private BinanceWebSocketClient bwsc;

    public RealtimeStreamWebSocketImpl() {
        this.proxy = ApplicationOptions.INSTANCE.getProxy();
    }

    @Override
    public void start() {
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
        final var pool =
                Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("rts-ws-pool-%d").build());
        return bwsc = new BinanceWebSocketClient(
                proxy,
                // on connect
                (client) -> client.subscribe(symbolListenersMap.keySet()),
                // on message
                (msg) -> onMessage(pool, msg),
                // on close
                (reason) -> this.reconnect(),
                // on error
                (e) -> {
                    // 不应该无限重连，需要设置最大重连次数，超过最大次数发送报警信息
                    log.error("ws error", e);
                    this.reconnect();
                }
        );
    }

    private void onMessage(ExecutorService pool, String message) {
        pool.submit(() -> {
            log.debug("receive message = {}", message);
            if (tryHandleTradeMessage(pool, message)) return;
            if (tryHandleBookTickerMessage(pool, message)) return;
            log.warn("can't handle message {} abandon!", message);
        });
    }

    private boolean tryHandleTradeMessage(ExecutorService pool, String message) {
        if (message.contains("trade")) {
            final var event = TradeMessage.ofJson(message);
            if (event.isTrade()) {
                if (symbolListenersMap.containsKey(event.symbol))
                    symbolListenersMap.get(event.symbol).forEach(it -> pool.submit(() -> it.update(event)));
                return true;
            } else {
                log.info("{} is not a trade message. abandon!", message);
            }
        }

        return false;
    }

    private boolean tryHandleBookTickerMessage(ExecutorService pool, String message) {
        final var event = BookTickerMessage.ofJson(message);
        if (event.isBookTickerMessage()) {
            if (symbolListenersMap.containsKey(event.symbol))
                symbolListenersMap.get(event.symbol).forEach(it -> pool.submit(() -> it.update(event)));
            return true;
        } else {
            log.info("{} is not a boot ticker message. abandon!", message);
        }
        return false;
    }

    private void reconnect() {
        if (!this.isClosed) {
            var client= connect();
            // 重新发起订阅
            client.subscribe(symbolListenersMap.keySet());
            log.info("websocket reconnected. id = {}", client.id);
        }
    }

    @Override
    public synchronized void addListener(final Symbol symbol, final RealtimeStreamListener listener) {
        log.info("add {} listener {}", symbol, listener);
        // 如果不存在 KEY 则需要订阅
        final var isNeedSubscribe = !symbolListenersMap.containsKey(symbol);

        // 如果不存在创建 listeners
        final var listeners =
                symbolListenersMap.computeIfAbsent(symbol, (k) -> Lists.newLinkedList());

        // 检查是否重复添加
        if (listeners.contains(listener)) {
            log.warn("already add listener {}", listener);
            return;
        }

        // 添加到集合并回写到 MAP
        listeners.add(listener);
        symbolListenersMap.put(symbol, listeners);

        // 订阅 symbol
        if (bwsc != null) {
            // TODO 此处可能漏掉订阅
            if (isNeedSubscribe) {
                bwsc.subscribe(symbol);
            }
        }
    }

    @Override
    public void removeListener(final Symbol symbol, final RealtimeStreamListener listener) {
        final var listeners = symbolListenersMap.get(symbol);
        if (listeners != null && !listeners.isEmpty()) {
            synchronized (this) {
                if (listeners.remove(listener)) {
                    if (listeners.isEmpty()) {
                        // listeners 为空移除订阅
                        bwsc.unsubscribe(symbol);
                        // 并从 MAP 中移除，否则重新添加订阅 isNeedSubscribe 将不生效。
                        symbolListenersMap.remove(symbol);
                    } else {
                        symbolListenersMap.put(symbol, listeners);
                    }
                }
            }
        }
    }
}
