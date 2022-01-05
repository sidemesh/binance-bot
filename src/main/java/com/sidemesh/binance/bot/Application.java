package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.dto.CreateBotRequest;
import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;
import io.javalin.Javalin;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Application {

    static {
        Sentry.init(options -> {
            options.setDsn("https://aafb5a8660a545f9913858d20a59c4bd@o1098944.ingest.sentry.io/6123318");
            options.setTracesSampleRate(1.0);
        });
    }

    public static void main(String[] args) {
        log.info("binance-bot v0.0.1");
        var opts = ApplicationOptions.formEnv();

        // 代理
        final var proxy = opts.isEnableLocalProxy() ? ClashProxy.newLocalClashProxy() : null;
        final var rts = new RealtimeStreamWebSocketImpl(proxy);
        rts.run();

        // bot hub
        final var botHub = new BotHub();

        // load file to botHub
        StoreService storeService = new StoreServiceJsonFileImpl();
        storeService.list().stream()
                .map(stat -> {
                    var apiClient = new BinanceAPIv3(proxy != null ? proxy.toProxy() : null,
                            Duration.ofSeconds(2), Duration.ofSeconds(2));
                    return new SimpleGridBot(stat, apiClient, Account.fromEnv(), rts);
                })
                .forEach(botHub::add);

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
                var bot =createBot(req.getSymbol(),
                        req.getName(),
                        proxy,
                        req.getAmountUSDT(),
                        req.getLowPrice(),
                        req.getHighPrice(),
                        req.getGrids(),
                        rts);
                botHub.add(bot);
            } catch (IllegalArgumentException e) {
                ctx.status(400);
                ctx.result(e.getMessage());
                return;
            }

            ctx.result("created!");
        });

        // 机器人列表
        app.get("/api/v1/bots", ctx -> {
            List<BotStat> botStats = botHub.all().stream()
                    .map(Bot::getBotStat)
                    .collect(Collectors.toList());
            ctx.json(botStats);
        });

        // 启动机器人
        app.post("/api/v1/bots/{name}/start", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.get(botName)
                    .ifPresentOrElse(bot -> {
                        bot.run();
                        ctx.result("start!");
                    }, () -> ctx.status(404));
        });

        // 停止机器人
        app.post("/api/v1/bots/{name}/stop", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.get(botName)
                    .ifPresentOrElse(bot -> {
                        bot.stop();
                        ctx.result("stop!");
                    }, () -> ctx.status(404));
        });

        // 删除机器人
        app.delete("/api/v1/bots/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.get(botName)
                    .ifPresentOrElse(bot -> {
                        botHub.remove(botName);
                        // 删除文件
                        storeService.delete(botName);
                        ctx.result("remove!");
                    }, () -> ctx.status(404));
        });
    }

    private static Bot createBot(Symbol symbol,
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
