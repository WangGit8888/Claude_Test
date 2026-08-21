package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 具体策略：支付宝
 */
public class AlipayStrategy implements PayStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("支付宝支付：" + amount + " 元");
    }
}
