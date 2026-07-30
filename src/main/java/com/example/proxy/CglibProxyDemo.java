package com.example.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class CglibProxyDemo {
    public static void main(String[] args) {
        // 真实对象（无接口）
        OrderService target = new OrderService();

        // 创建增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("【CGLIB代理】前置处理: " + method.getName());
                Object result = proxy.invokeSuper(obj, args);  // 注意是invokeSuper
                System.out.println("【CGLIB代理】后置处理");
                return result;
            }
        });

        // 生成代理对象（是OrderService的子类）
        OrderService proxy = (OrderService) enhancer.create();

        // 调用代理方法
        proxy.createOrder("ORD-001");
        System.out.println(proxy.getOrder("ORD-001"));
    }
}
