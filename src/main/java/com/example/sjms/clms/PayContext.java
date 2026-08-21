package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 上下文：持有支付策略，对外暴露统一支付入口
 */
public class PayContext {

    private final PayStrategy strategy;

    public PayContext(PayStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(BigDecimal amount) {
        strategy.pay(amount); // 委托给具体策略
    }
}
