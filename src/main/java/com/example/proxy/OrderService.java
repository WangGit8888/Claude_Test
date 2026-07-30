package com.example.proxy;

// 无接口的类（用于CGLIB代理）
public class OrderService {
    public void createOrder(String orderId) {
        System.out.println("【真实对象】创建订单: " + orderId);
    }

    public String getOrder(String orderId) {
        System.out.println("【真实对象】查询订单: " + orderId);
        return "订单-" + orderId;
    }
}
