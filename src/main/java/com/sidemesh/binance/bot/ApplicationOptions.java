package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;

import java.net.Proxy;

public class ApplicationOptions {

    // -enable-local-proxy
    // 是否开启本地代理
    public final boolean isEnableLocalProxy;

    public ApplicationOptions(boolean isEnableLocalProxy) {
        this.isEnableLocalProxy = isEnableLocalProxy;
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
        INSTANCE =
                new ApplicationOptions(value != null && !value.isEmpty() && Boolean.parseBoolean(value));
    }
}
