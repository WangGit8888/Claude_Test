package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 策略模式：支付策略接口
 * 不同支付方式各自实现支付逻辑
 */
public interface PayStrategy {
    void pay(BigDecimal amount);
}
