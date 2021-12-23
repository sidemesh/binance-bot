package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.dto.CreateBotRequest;
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

        // 代理
        final var proxy = opts.isEnableLocalProxy() ? ClashProxy.newLocalClashProxy() : null;
        final var rts = new RealtimeStreamWebSocketImpl(proxy);
        rts.run();

        Javalin app = Javalin.create(cfg -> {
            cfg.showJavalinBanner = true;
        }).start(8080);

        // 创建机器人
        app.put("/api/v1/bots", ctx -> {
            var req = ctx.bodyValidator(CreateBotRequest.class)
                    .check(it -> {
                        var r = it.getLowPrice().compareTo(it.getHighPrice());
                        return r < 0;
                    }, "high price must greater than low price")
                    .check(it -> {
                        var r = it.getAmountUSDT().compareTo(BigDecimal.ZERO);
                        return r > 0;
                    }, "amount USDT must greater than 0")
                    .get();

            try {
                var bot=createGridBot(req.getSymbol(),
                        req.getName(),
                        proxy,
                        req.getAmountUSDT(),
                        req.getLowPrice(),
                        req.getHighPrice(),
                        req.getGrids(),
                        rts);
                bot.run();
                BotHub.shared.add(bot);
            } catch (IllegalArgumentException e) {
                ctx.status(400);
                ctx.result(e.getMessage());
                return;
            }

            ctx.result("created!");
        });
    }

    private static GridBot createGridBot(Symbol symbol,
                                       String name,
                                       ProxyInfo proxyInfo,
                                       BigDecimal amountUSDT,
                                       BigDecimal lowPrice,
                                       BigDecimal highPrice,
                                       int grids,
                                       RealtimeStream rtl) {
        log.info("create new bot symbol:{} name:{} amountUSDT:{} lowPrice:{} highPrice:{} grids:{}"
                , symbol, name, amountUSDT, lowPrice, highPrice, grids );
        var apiClient = new BinanceAPIv3(proxyInfo!= null ? proxyInfo.toProxy() : null,
                Duration.ofSeconds(2), Duration.ofSeconds(2));
        return new SimpleGridBot(
                name,
                symbol,
                Account.fromEnv(),
                apiClient,
                amountUSDT,
                lowPrice,
                highPrice,
                grids,
                rtl);
    }

}
