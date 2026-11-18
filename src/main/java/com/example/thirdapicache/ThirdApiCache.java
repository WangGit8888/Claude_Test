package com.example.thirdapicache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThirdApiCache {
    String value();                    // 接口标识，如 enterprise:detail
    long ttl() default 300;            // 有效期，秒
    long jitter() default 30;          // 随机增加 0~jitter 秒，防集中过期
    boolean cacheNull() default false; // 默认不缓存空结果
}


//@ThirdApiCache(value = "enterprise:detail", ttl = 3600, jitter = 120)
//public EnterpriseInfo queryEnterprise(String creditCode) {
//    return thirdPartyClient.query(creditCode);
//}