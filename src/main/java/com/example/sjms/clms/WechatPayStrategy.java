package com.example.sjms.clms;

import java.math.BigDecimal;

/**
 * 具体策略：微信支付
 */
public class WechatPayStrategy implements PayStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("微信支付：" + amount + " 元");
    }
}
