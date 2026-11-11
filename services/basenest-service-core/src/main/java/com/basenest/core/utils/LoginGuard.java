package com.basenest.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LoginGuard
 * @Description In-memory brute-force guard
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Component
public class LoginGuard {

    @Value("${app.auth.login-max-failed}")
    private int maxFailed;

    @Value("${app.auth.login-lock-minutes}")
    private long lockMinutes;

    private static class Entry {
        int failed;
        Long lockedUntil;
    }

    private final Map<String, Entry> map = new ConcurrentHashMap<>();

    /**
     * @return null if allowed, else remaining seconds
     */
    public Long checkAllowed(String username) {
        Entry e = map.get(username);
        if (e == null || e.lockedUntil == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        if (e.lockedUntil > now) {
            return (e.lockedUntil - now) / 1000;
        }
        return null;
    }

    public void recordSuccess(String username) {
        map.remove(username);
    }

    public void recordFailure(String username) {
        long lockMillis = lockMinutes * 60 * 1000;
        map.compute(username, (k, e) -> {
            if (e == null) {
                e = new Entry();
            }
            e.failed++;
            if (e.failed >= maxFailed) {
                e.lockedUntil = System.currentTimeMillis() + lockMillis;
                e.failed = 0;
            }
            return e;
        });
    }
}