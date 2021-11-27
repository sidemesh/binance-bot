package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("binance-bot v0.0.1");
        var opts = ApplicationOptions.formEnv();

        ProxyInfo proxy = opts.isEnableLocalProxy() ? ClashProxy.newLocalClashProxy() : null;
        var rts = new RealtimeStreamWebSocketImpl(proxy);

        var bot =  new SimpleGridBot(
                "simple-grid-bot-0",
                Symbol.ANKR_USDT,
                Account.fromEnv(),
                new BinanceAPIv3(proxy != null ? proxy.toProxy() : null, Duration.ofSeconds(10), Duration.ofSeconds(10)),
                new BigDecimal(20000),
                new BigDecimal("0.18"),
                new BigDecimal("0.21"),
                100,
                rts
        );
        rts.run();
        bot.run();
    }

    private static void runApiServer() {
        Javalin app = Javalin.create(cfg -> {
            cfg.showJavalinBanner = true;
        }).start(8080);
        app.get("/api/v1/bots", ctx -> {
            ctx.result("TODO");
        });
    }

}
