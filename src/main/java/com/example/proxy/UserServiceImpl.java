package com.example.proxy;

// 实现类（用于JDK代理）
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String name) {
        System.out.println("【真实对象】添加用户: " + name);
    }

    @Override
    public String getUser(String id) {
        System.out.println("【真实对象】查询用户: " + id);
        return "用户-" + id;
    }
}
