package com.example.juc.zxsx;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1); // 初始有1张通行证
        Semaphore s2 = new Semaphore(0); // 初始0张通行证
        Semaphore s3 = new Semaphore(0); // 初始0张通行证

        Thread t1 = new Thread(() -> {
            try {
                s1.acquire(); // 拿通行证（初始有1个，立刻拿到）
                System.out.println("A");
                s2.release(); // 释放一张给t2
            } catch (InterruptedException e) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                s2.acquire(); // 等待t1释放通行证
                System.out.println("B");
                s3.release(); // 释放一张给t3
            } catch (InterruptedException e) {}
        });

        Thread t3 = new Thread(() -> {
            try {
                s3.acquire(); // 等待t2释放通行证
                System.out.println("C");
            } catch (InterruptedException e) {}
        });

        t1.start(); t2.start(); t3.start();
    }
}
