package com.example.limiter;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    /**
     * 限流 Key，支持 SpEL 表达式，如 "#userId"
     */
    String key();

    /**
     * 令牌生成速率（每秒多少个）
     */
    double rate() default 10.0;

    /**
     * 桶容量
     */
    int capacity() default 20;

    /**
     * 每次请求消耗的令牌数
     */
    int requestedTokens() default 1;
}
