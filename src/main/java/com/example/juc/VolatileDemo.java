package com.example.juc;

public class VolatileDemo {
    /**
     * volatile 控制可见性
     */
    private volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (!flag) {
                count++; // 纯CPU计算，没有锁/IO操作
            }
            System.out.println("子线程退出，count=" + count);
        });
        t1.start();

        Thread.sleep(2000); // 让子线程先跑一会儿

        Thread t2 = new Thread(() -> {
            flag = true;
            System.out.println("主线程已将 flag 设为 true");
        });
        t2.start();
    }
}
