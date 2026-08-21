package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 策略模式演示：支付方式可灵活切换
 */
public class StrategyDemo {

    public static void main(String[] args) {
        // 支付宝
        PayContext context = new PayContext(new AlipayStrategy());
        context.pay(new BigDecimal("99.50"));

        // 微信
        context = new PayContext(new WechatPayStrategy());
        context.pay(new BigDecimal("199.00"));

        // 银行卡
        context = new PayContext(new CardPayStrategy());
        context.pay(new BigDecimal("500.00"));
    }
}
