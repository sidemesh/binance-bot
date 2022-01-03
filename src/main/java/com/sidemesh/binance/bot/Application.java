package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.api.BinanceAPIv3;
import com.sidemesh.binance.bot.dto.CreateBotRequest;
import com.sidemesh.binance.bot.proxy.ClashProxy;
import com.sidemesh.binance.bot.proxy.ProxyInfo;
import com.sidemesh.binance.bot.store.StoreService;
import com.sidemesh.binance.bot.store.StoreServiceJsonFileImpl;
import com.sidemesh.binance.bot.websocket.RealtimeStreamWebSocketImpl;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class Application {

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
        app.put("/api/v1/bots/start/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            Optional<Bot> bot = botHub.get(botName);
            if (bot.isEmpty()) {
                ctx.status(404);
            } else {
                bot.get().run();
                ctx.result("start!");
            }
        });

        // 停止机器人
        app.put("/api/v1/bots/stop/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            Optional<Bot> bot = botHub.get(botName);
            if (bot.isEmpty()) {
                ctx.status(404);
            } else {
                bot.get().stop();
                ctx.result("stop!");
            }
        });

        // 删除机器人
        app.delete("/api/v1/bots/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.remove(botName);
            ctx.result("remove!");
        });

        // 销毁机器人
        app.delete("/api/v1/bots/destroy/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            botHub.remove(botName);
            // 删除文件
            storeService.delete(botName);
            ctx.result("destroy!");
        });

        // 加载机器人
        app.put("/api/v1/botfiles/load/{name}", ctx -> {
            String botName = ctx.pathParam("name");
            BotStat botStat = storeService.getByName(botName);
            var apiClient = new BinanceAPIv3(proxy != null ? proxy.toProxy() : null,
                    Duration.ofSeconds(2), Duration.ofSeconds(2));
            SimpleGridBot bot = new SimpleGridBot(botStat, apiClient, Account.fromEnv(), rts);
            botHub.add(bot);
            ctx.result("load!");
        });

        // 所有已配置机器人
        app.get("/api/v1/botfiles/", ctx -> {
            List<BotStat> list = storeService.list();
            for (BotStat botStat : list) {
                BotStatusEnum botStatusEnum = botHub.get(botStat.name)
                        .map(Bot::getBotStatus)
                        .orElse(null);
                botStat.setStatus(botStatusEnum);
            }
            ctx.json(list);
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
