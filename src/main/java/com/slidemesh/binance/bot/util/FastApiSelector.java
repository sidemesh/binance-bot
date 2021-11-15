package com.slidemesh.binance.bot.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FastApiSelector implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(FastApiSelector.class);

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

    public void onUpdate(Consumer<String> cb) {
        this.callbacks.add(cb);
    }

    public String loop() {
        return loop(3, 10);
    }

    /**
     * 立即选出一个最优结果，并开启定时器进行不断选取
     * @param initialDelay 定时器在多久后执行
     * @param period 定时器多久执行一次
     * @return 快速选择的API
     */
    public synchronized String loop(long initialDelay, long period) {
        if (isRunning) {
            throw new RuntimeException("already running!");
        }

        var se = Executors.newSingleThreadScheduledExecutor();
        this.sf = se.scheduleAtFixedRate(this,initialDelay,period, TimeUnit.SECONDS);

        // 暂时不考虑并发测试
        // this.works = Executors.newFixedThreadPool(this.apis.length);
        return test(3);
    }

    public synchronized void stop() {
        if (isRunning) {
            sf.cancel(true);
            isRunning = false;
        }
    }

    @Override
    public void run() {
        final var selected = test(10);
        callbacks.forEach(it -> it.accept(selected));
    }

    /**
     * 请求接口测试时间间隔，使用最小值选取。
     * 是否应该按照平均最小值选取？
     * @param countOfTest 测试次数
     * @return 最快的 host
     */
    private String test(int countOfTest) {
        // 存放结果
        int[] totals = new int[apis.length];

        for (var i = 0; i < apis.length; i++) {
            for (var j = 0; j < countOfTest; j++) {
                final var startAt = new Date().getTime();
                try(var ignored = cli.newCall(reqs[i]).execute()) {
                    final var interval = new Date().getTime() - startAt;
                    log.debug("{} test.{} interval {}", apis[i], j, interval);
                    totals[i] += interval;
                } catch (IOException e) {
                    totals[i] += 99999;
                    log.error("api test error", e);
                }

                // 取平均值
                // totals[i] = totals[i] / countOfTest;
            }
        }

        int faster = 0;
        for (var i = 0; i< totals.length; i++) {
            if (totals[i] < faster) {
                faster = i;
            }
        }

        return apis[faster];
    }

    private Request[] buildTestRequests(String[] apis) {
        final var reqs = new Request[apis.length];
        for (int i = 0; i < apis.length; i++) {
            reqs[i] = new Request.Builder().url(apis[i]).build();
        }
        return reqs;
    }
}
