package com.sidemesh.binance.bot.websocket;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sidemesh.binance.bot.RealtimeStream;
import com.sidemesh.binance.bot.RealtimeStreamListener;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.websocket.event.EventMessage;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RealtimeStreamWebSocketImpl implements RealtimeStream {

    private static final Logger log = LoggerFactory.getLogger(RealtimeStreamWebSocketImpl.class);

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
        symbolListenersMap.put(Symbol.GALA_USDT, null);
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
        final var pool =
                Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("rts-ws-pool-%d").build());
        return bwsc = new BinanceWebSocketClient(
                proxyInfo,
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
            final var event = EventMessage.ofJson(message);
            if (event.isTrade()) {
                final var ls = symbolListenersMap.get(event.symbol);
                if (ls != null) {
                    ls.forEach(it -> pool.submit(() -> it.update(event)));
                }
            } else {
                log.debug("{} is not a trade message. skip!", message);
            }
        });
    }

    private void reconnect() {
        if (!this.isClosed) {
            var client= connect();
            log.info("ws reconnected. id = {}", client.id);
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

}
