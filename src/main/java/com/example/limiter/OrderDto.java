package com.example.limiter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 订单金额（单位：元）
     */
    private BigDecimal amount;

    /**
     * 收货地址
     */
    private String address;
}