package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 具体策略：银行卡支付
 */
public class CardPayStrategy implements PayStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("银行卡支付：" + amount + " 元");
    }
}
