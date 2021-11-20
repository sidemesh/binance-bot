package com.sidemesh.binance.bot.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FastApiSelector implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(FastApiSelector.class);

    // 默认接口测试次数
    private static final int DEFAULT_TESTS = 10;
    // 默认快速接口测试次数
    private static final int DEFAULT_FAST_TESTS = 3;

    // flag
    private boolean isRunning;
    // 测试列表
    private final String[] apis;
    // 预生成可复用的 request
    private final Request[] reqs;
    // http client
    private final OkHttpClient cli;
    // 选取回调
    private final Set<Consumer<String>> callbacks;
    // 可供取消的定时器线程
    private ScheduledFuture<?> sf;

    public FastApiSelector(OkHttpClient cli, String ...apis) {
        this.apis = apis;
        this.cli = cli;
        this.reqs = buildTestRequests(apis);
        this.callbacks = new HashSet<>();
    }

    public FastApiSelector onUpdate(Consumer<String> cb) {
        this.callbacks.add(cb);
        return this;
    }

    /**
     * 立即选出一个最优结果
     * @return 最优地址
     */
    public String once() {
        return test(DEFAULT_FAST_TESTS);
    }

    /**
     * 并开启定时器进行不断选取
     * @param initialDelay 定时器在多久后执行
     * @param period 定时器多久执行一次
     */
    public synchronized void loop(long initialDelay, long period) {
        if (isRunning) {
            throw new RuntimeException("already running!");
        }
        var se = Executors.newSingleThreadScheduledExecutor();
        this.sf = se.scheduleAtFixedRate(this,initialDelay,period, TimeUnit.SECONDS);
    }

    public synchronized void stop() {
        if (isRunning) {
            sf.cancel(true);
            isRunning = false;
        }
    }

    @Override
    public void run() {
        final var selected = test(DEFAULT_TESTS);
        callbacks.forEach(it -> it.accept(selected));
    }

    /**
     * 请求接口测试时间间隔，使用最小值选取。
     * 是否应该按照平均最小值选取？
     * @param countOfTest 测试次数
     * @return 最快的地址
     */
    private String test(int countOfTest) {
        // 存放结果
        final var testSums = new int[apis.length];

        for (var i = 0; i < apis.length; i++) {
            for (var j = 0; j < countOfTest; j++) {
                final var startAt = Instant.now();
                try(var ignored = cli.newCall(reqs[i]).execute()) {
                    final var interval = Duration.between(startAt, Instant.now()).toMillis();
                    log.debug("{} test.{} interval {}ms", apis[i], j, interval);
                    testSums[i] += interval;
                } catch (IOException e) {
                    testSums[i] += 99999;
                    log.error("api test error", e);
                }
            }
        }

        return apis[minIndex(testSums)];
    }

    private Request[] buildTestRequests(String[] apis) {
        final var reqs = new Request[apis.length];
        for (int i = 0; i < apis.length; i++) {
            reqs[i] = new Request.Builder().url(apis[i]).build();
        }
        return reqs;
    }

    /**
     * 查找数组中最小值的下标
     * @param nums int数组
     * @return 数组中最小值的下标
     */
    private int minIndex(int[] nums) {
        int faster = 0;
        for (var i = 0; i < nums.length; i++) {
            if (nums[i] < faster) {
                faster = i;
            }
        }
        return faster;
    }
}
