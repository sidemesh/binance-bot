package com.sidemesh.binance.bot;

import com.github.kevinsawicki.http.HttpRequest;
import com.sidemesh.binance.bot.json.JSON;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * App 集成测试
 */
public class ApplicationIntegrateTest {
    private static final String BASE_URL = "http://127.0.0.1:8080";
    private static final String BOT_NAME = "ApplicationIntegrateTest-001";

    @BeforeAll
    static void before() {
        Application.main(new String[0]);
        String json = "{\n" +
                "    \"name\": \"ApplicationIntegrateTest-001\",\n" +
                "    \"symbol\": \"ZECETH\",\n" +
                "    \"amountUSDT\": 1000,\n" +
                "    \"lowPrice\": 1,\n" +
                "    \"highPrice\": 3,\n" +
                "    \"grids\": 10\n" +
                "}";
        String body = HttpRequest.put(BASE_URL + "/api/v1/bots")
                .send(json)
                .body();
        assertEquals("created!", body);
    }

    @AfterAll
    static void after() {
        String body = HttpRequest.delete(BASE_URL + "/api/v1/bots/" + BOT_NAME)
                .body();
        assertEquals("remove!", body);
    }

    @Test
    public void listAll() {
        String body = HttpRequest.get(BASE_URL + "/api/v1/bots")
                .body();
        assertNotNull(body);
    }

    @Test
    public void canStartStopBot() {
        // 开启机器人
        String body = HttpRequest.put(BASE_URL + "/api/v1/bots/" + BOT_NAME + "/start/").body();
        assertEquals("start!", body);
        BotStat botStat = getBot();
        assertNotNull(botStat);
        assertEquals(BotStatusEnum.RUNNING, botStat.status);

        // 暂停机器人
        body = HttpRequest.put(BASE_URL + "/api/v1/bots/" + BOT_NAME + "/stop/").body();
        assertEquals("stop!", body);
        botStat = getBot();
        assertNotNull(botStat);
        assertEquals(BotStatusEnum.STOP, botStat.status);

        // 删除机器人
        HttpRequest.delete(BASE_URL + "/api/v1/bots/" + BOT_NAME).body();
        botStat = getBot();
        assertNull(botStat);
    }

    private BotStat getBot() {
        String body = HttpRequest.get(BASE_URL + "/api/v1/bots")
                .body();
        BotStat[] allBots = JSON.jackson.read(body, BotStat[].class);
        for (BotStat botStat : allBots) {
            if (BOT_NAME.equals(botStat.name)) {
                return botStat;
            }
        }
        return null;
    }
}
