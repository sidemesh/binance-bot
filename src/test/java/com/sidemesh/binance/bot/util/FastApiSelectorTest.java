package com.sidemesh.binance.bot.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.slidemesh.binance.bot.util.FastApiSelector;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class FastApiSelectorTest {

    @Test
    public void testRunnable() {
        var httpCli = new OkHttpClient.Builder().build();
        var selector = new FastApiSelector(httpCli, "https://baidu.com", "https://qq.com", "https://huawei.com", "https://163.com");

        final var res = new String[1];

        selector.onUpdate((host) -> res[0] = host);
        selector.run();

        assertNotNull(res[0]);
    }

    @Test
    public void testLoop() throws InterruptedException {
        var httpCli = new OkHttpClient.Builder().build();
        var selector = new FastApiSelector(httpCli, "https://baidu.com", "https://qq.com");

        final var cd = new CountDownLatch(1);
        final var res = new String[1];
        selector.onUpdate((host) -> {
            res[0] = host;
            cd.countDown();
        });

        // 测试快速返回
        assertNotNull(selector.loop(0, 365));

        // 测试异步
        // TODO 需要设置超时
        cd.await();
        assertNotNull(res[0]);

        // 停止
        selector.stop();
    }

}
