package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPI;
import com.sidemesh.binance.bot.dto.CreateBotRequest;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;
import io.javalin.Javalin;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Slf4j
public class Application {

    private static void sentryInit() {
        Sentry.init(options -> {
            options.setDsn(ApplicationOptions.INSTANCE.sentryDsn);
            options.setTracesSampleRate(1.0);
            options.setDebug(true);
        });
    }

    public static void main(String[] args) {
        log.info("binance-bot v0.0.1");
        // sentry
        sentryInit();
        // rts
        final var rts = new RealtimeStreamWebSocketImpl();
        // binance api
        final var binanceApi = BinanceAPI.V3;
        // account
        final var account = Account.fromEnv();
        // bot hub
        final var botHub = new BotHub();

        // 启动实时流
        rts.start();
        // 从文件中加载 bot
        StoreService storeService = new StoreServiceJsonFileImpl();
        storeService.list().stream()
                .map(stat -> new SimpleGridBot(stat, binanceApi, account, rts))
                .forEach(botHub::add);

        // 启动 API 服务
        Javalin app = Javalin.create(cfg -> cfg.showJavalinBanner = true).start(8080);
        // 创建并启动机器人
        app.put("/api/v1/bots", ctx -> {
            var req = ctx.bodyValidator(CreateBotRequest.class).get();
            try {
                var bot= createBot(
                        binanceApi,
                        req.getSymbol(),
                        req.getName(),
                        req.getAmountUSDT(),
                        req.getLowPrice(),
                        req.getHighPrice(),
                        req.getGrids(),
                        rts
                );
                botHub.add(bot);
                bot.start();
                ctx.result("created!");
            } catch (IllegalArgumentException e) {
                ctx.status(400);
                ctx.result(e.getMessage());
            }
        });

        // 机器人列表
        app.get("/api/v1/bots", ctx -> {
            var stats = botHub.all().stream()
                    .map(Bot::stat)
                    .collect(Collectors.toList());
            ctx.json(stats);
        });

        // 启动机器人
        app.post("/api/v1/bots/{name}/start", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.get(botName)
                    .ifPresentOrElse(bot -> {
                        bot.start();
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
                        ctx.result("removed!");
                    }, () -> ctx.status(404));
        });
    }

    private static Bot createBot(BinanceAPI api,
                                 Symbol symbol,
                                 String name,
                                 BigDecimal amountUSDT,
                                 BigDecimal lowPrice,
                                 BigDecimal highPrice,
                                 int grids,
                                 RealtimeStream rtl) {
        log.info("create new bot symbol:{} name:{} invest:{} lowPrice:{} highPrice:{} grids:{}",
                symbol, name, amountUSDT, lowPrice, highPrice, grids);
        return new SimpleGridBot(
                name,
                symbol,
                Account.fromEnv(),
                api,
                amountUSDT,
                lowPrice,
                highPrice,
                grids,
                rtl
        );
    }

}
