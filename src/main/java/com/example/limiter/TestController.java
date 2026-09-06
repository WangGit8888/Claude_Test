package com.example.limiter;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    /**
     * 按用户ID限流：每秒10个请求，桶容量20
     */
    @RateLimit(key = "#userId", rate = 10, capacity = 20)
    @GetMapping("/api/test")
    public String test(@RequestParam String userId) {
        return "请求成功！userId=" + userId;
    }

    /**
     * 按接口限流：每秒100个请求
     */
    @RateLimit(key = "'api:/api/order/create'", rate = 100, capacity = 150)
    @PostMapping("/api/order/create")
    public String createOrder(@RequestBody OrderDto dto) {
        return "订单创建成功";
    }

    /**
     * 按 IP 限流（需要从请求中获取 IP）
     */
    @RateLimit(key = "#ip", rate = 5, capacity = 10)
    @GetMapping("/api/limited")
    public String limited(@RequestParam String ip) {
        return "请求成功";
    }
}
