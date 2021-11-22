package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("binance-bot v0.0.1");
        var opts = ApplicationOptions.formEnv();

        ProxyInfo proxy = opts.isEnableLocalProxy() ? ClashProxy.newLocalClashProxy() : null;
        var realtimeStream = new RealtimeStreamWebSocketImpl(proxy);
        realtimeStream.run();

        if (opts.isEnableApiServer()) {
            runApiServer();
        }
    }

    private static void runApiServer() {
        Javalin app = Javalin.create(cfg -> {
            cfg.showJavalinBanner = true;
        }).start(8080);
        app.get("/api/v1/bots", ctx -> ctx.result("TODO"));
    }

}
