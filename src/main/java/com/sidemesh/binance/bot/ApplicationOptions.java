package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;

import java.net.Proxy;

public class ApplicationOptions {

    // -enable-local-proxy
    // 是否开启本地代理
    public final boolean isEnableLocalProxy;
    public final String sentryDsn;

    public ApplicationOptions(boolean isEnableLocalProxy, String sentryDsn) {
        this.isEnableLocalProxy = isEnableLocalProxy;
        this.sentryDsn = sentryDsn;
    }

    public ProxyInfo getProxyInfo() {
        if (isEnableLocalProxy) {
            return ClashProxy.newLocalClashProxy();
        }
        return null;
    }

    public Proxy getProxy() {
        final var p = getProxyInfo();
        return p != null ? p.toProxy() : null;
    }

    public static final ApplicationOptions INSTANCE;

    static {
        var value = System.getenv("IS_ENABLE_LOCAL_PROXY");
        var isEnableLocalProxy = value != null && !value.isEmpty() && Boolean.parseBoolean(value);
        var sentryDsn = System.getenv("SENTRY_DSN");
        INSTANCE = new ApplicationOptions(isEnableLocalProxy, sentryDsn);
    }
}
