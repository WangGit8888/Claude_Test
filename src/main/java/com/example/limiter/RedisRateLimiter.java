package com.example.limiter;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;


import java.util.Collections;

@Component
public class RedisRateLimiter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private DefaultRedisScript<Long> rateLimiterScript;

    @PostConstruct
    public void init() {
        // 加载 Lua 脚本
        String script =
                "local key = KEYS[1] " +
                        "local current_tokens_key = key .. ':tokens' " +
                        "local refill_time_key = key .. ':last_refill_timestamp' " +
                        "local rate = tonumber(ARGV[1]) " +
                        "local capacity = tonumber(ARGV[2]) " +
                        "local redis_time = redis.call('TIME') " +
                        "local now = tonumber(redis_time[1]) + tonumber(redis_time[2]) / 1000000 " +
                        "local requested_tokens = tonumber(ARGV[3]) " +
                        "local current_tokens = tonumber(redis.call('get', current_tokens_key) or capacity) " +
                        "local last_refill_timestamp = tonumber(redis.call('get', refill_time_key) or now) " +
                        "local delta_time = now - last_refill_timestamp " +
                        "local new_tokens = math.min(capacity, current_tokens + (delta_time * rate)) " +
                        "redis.call('set', refill_time_key, now) " +
                        "if new_tokens >= requested_tokens then " +
                        "  new_tokens = new_tokens - requested_tokens " +
                        "  redis.call('set', current_tokens_key, new_tokens) " +
                        "  return 1 " +
                        "else " +
                        "  redis.call('set', current_tokens_key, new_tokens) " +
                        "  return 0 " +
                        "end";

        rateLimiterScript = new DefaultRedisScript<>(script, Long.class);
    }

    /**
     * 尝试获取令牌
     * @param key 限流标识（如 user_123 或 api:/user/info）
     * @param rate 令牌生成速率（每秒多少个）
     * @param capacity 桶容量
     * @param requestedTokens 本次请求需要的令牌数（通常为1）
     * @return true=成功（通过），false=失败（被限流）
     */
    public boolean tryAcquire(String key, double rate, int capacity, int requestedTokens) {
        try {
            Long result = redisTemplate.execute(
                    rateLimiterScript,
                    Collections.singletonList(key),
                    String.valueOf(rate),
                    String.valueOf(capacity),
                    String.valueOf(requestedTokens)
            );
            return result != null && result == 1L;
        } catch (Exception e) {
            // Redis 异常时，建议降级：放行或记录日志
            return true;
        }
    }

    /**
     * 简化调用：每次请求消耗1个令牌
     */
    public boolean tryAcquire(String key, double rate, int capacity) {
        return tryAcquire(key, rate, capacity, 1);
    }
}