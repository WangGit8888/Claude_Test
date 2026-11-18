package com.example.thirdapicache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Aspect
@Component
@RequiredArgsConstructor
public class ThirdApiCacheAspect {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Around("@annotation(cache)")
    public Object around(ProceedingJoinPoint pjp, ThirdApiCache cache) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class<?> returnType = signature.getReturnType();

        String key = buildKey(cache.value(), pjp.getArgs());
        String cached = redisTemplate.opsForValue().get(key);

        if (StringUtils.hasText(cached)) {
            return objectMapper.readValue(cached, returnType);
        }

        Object result = pjp.proceed();

        if (result != null || cache.cacheNull()) {
            long ttl = cache.ttl()
                    + ThreadLocalRandom.current().nextLong(cache.jitter() + 1);

            redisTemplate.opsForValue().set(
                    key,
                    objectMapper.writeValueAsString(result),
                    Duration.ofSeconds(ttl)
            );
        }
        return result;
    }

    private String buildKey(String api, Object[] args) {
        // 参数转 JSON 后摘要，避免 Key 过长；注意 JSON 字段顺序要稳定
        String paramDigest = DigestUtils.md5DigestAsHex(
                Arrays.deepToString(args).getBytes(StandardCharsets.UTF_8)
        );
        return "third:cache:" + api + ":" + paramDigest;
    }
}

