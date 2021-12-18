package com.sidemesh.binance.bot;

public class ApplicationOptions {

    // -enable-local-proxy
    // 是否开启本地代理
    private boolean isEnableLocalProxy = true;

    public static ApplicationOptions formEnv() {
        ApplicationOptions opts = new ApplicationOptions();

        var value= System.getenv("IS_ENABLE_LOCAL_PROXY");
        opts.isEnableLocalProxy = value != null && !value.isEmpty() && Boolean.parseBoolean(value);

        return opts;
    }

    public boolean isEnableLocalProxy() {
        return isEnableLocalProxy;
    }
}
