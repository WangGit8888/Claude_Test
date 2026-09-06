package com.example.limiter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;

@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RedisRateLimiter rateLimiter;

    private final ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        // 1. 解析 SpEL 表达式，生成限流 Key
        String key = parseKey(rateLimit.key(), joinPoint);
        String fullKey = "rate_limit:" + key;

        // 2. 尝试获取令牌
        boolean allowed = rateLimiter.tryAcquire(
                fullKey,
                rateLimit.rate(),
                rateLimit.capacity(),
                rateLimit.requestedTokens()
        );

        if (!allowed) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "请求过于频繁，请稍后再试"
            );
        }

        // 3. 通过限流，继续执行
        return joinPoint.proceed();
    }

    private String parseKey(String expression, ProceedingJoinPoint joinPoint) {
        // 如果表达式是纯字符串（不含 #），直接返回
        if (!expression.contains("#")) {
            return expression;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();

        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }

        Expression exp = parser.parseExpression(expression);
        return exp.getValue(context, String.class);
    }
}