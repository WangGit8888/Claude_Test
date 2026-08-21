package com.example.juc.zxsx;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println("A");
            latch1.countDown(); // 减1，门闩打开
        });

        Thread t2 = new Thread(() -> {
            try {
                latch1.await(); // 等待门闩1打开
                System.out.println("B");
                latch2.countDown(); // 打开门闩2
            } catch (InterruptedException e) {}
        });

        Thread t3 = new Thread(() -> {
            try {
                latch2.await(); // 等待门闩2打开
                System.out.println("C");
            } catch (InterruptedException e) {}
        });

        t3.start();
        t2.start();
        t1.start(); // 虽然按反序启动，但因为有门闩，最终输出一定是 A B C
    }
}
