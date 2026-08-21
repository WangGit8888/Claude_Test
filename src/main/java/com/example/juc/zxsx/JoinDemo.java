package com.example.juc.zxsx;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("A"));
        Thread t2 = new Thread(() -> System.out.println("B"));
        Thread t3 = new Thread(() -> System.out.println("C"));

        t1.start();
        t1.join();  // main线程阻塞，等t1跑完

        t2.start();
        t2.join();  // main线程阻塞，等t2跑完

        t3.start();
        t3.join();  // main线程阻塞，等t3跑完
    }
}
