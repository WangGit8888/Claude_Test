package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {
    public static void main(String[] args) {
        // 真实对象
        UserService target = new UserServiceImpl();

        // 创建代理
        UserService proxy = (UserService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("【JDK代理】前置处理: " + method.getName());
                        Object result = method.invoke(target, args);
                        System.out.println("【JDK代理】后置处理");
                        return result;
                    }
                }
        );

        // 调用代理方法
        proxy.addUser("张三");
        System.out.println(proxy.getUser("1001"));
    }
}
