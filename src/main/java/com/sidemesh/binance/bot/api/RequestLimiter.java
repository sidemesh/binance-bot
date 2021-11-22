package com.sidemesh.binance.bot.api;

import java.time.Instant;

class RequestLimiter {

    public final int perSecond;
    public volatile int current;
    public volatile long preSyncTz;

    public RequestLimiter(int perSecond) {
        this.perSecond = perSecond;
    }

    public boolean acquire() {
        if (current > 0) {
            synchronized (this) {
                if (current > 0) {
                    current = current - 1;
                    return true;
                }
            }
        } else {
            return sync();
        }

        return false;
    }

    private boolean sync() {
        final var now = Instant.now().getEpochSecond();
        if ((now - preSyncTz) > 0) {
            synchronized (this) {
                if (current > 0) {
                    // 已进行同步
                    current = current - 1;
                } else {
                    // 未进行同步
                    current = perSecond - 1;
                    preSyncTz = now;
                }
                return true;
            }
        }

        return false;
    }

}
