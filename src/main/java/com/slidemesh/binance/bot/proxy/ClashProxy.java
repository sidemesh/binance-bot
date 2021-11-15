package com.slidemesh.binance.bot.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ClashProxy implements ProxyInfo {

    public final String host;
    public final int port;

    public ClashProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public InetSocketAddress toInetSocketAddress() {
        return new InetSocketAddress(this.host, this.port);
    }

    public Proxy toProxy() {
        return new Proxy(Proxy.Type.SOCKS, this.toInetSocketAddress());
    }

}
