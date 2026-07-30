package com.example.juc;

public class InterruptNotStopDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("子线程开始运行");
            long count = 0;
            while (true) {
                count++;
                // 每隔一段时间检查一次中断标志
                if (count % 100_000_000 == 0) {
                    System.out.println("子线程还在跑, count=" + count);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("检测到中断！但我选择继续...");
                        // 或者 break; 退出
                    }
                }
            }
        });
        t1.start();

        Thread.sleep(100); // 让子线程跑一会儿
        t1.interrupt();    // 发送中断信号
        System.out.println("主线程已发送 interrupt，但子线程不会停");

        Thread.sleep(1000);
        System.out.println("主线程结束");
        // 子线程还在后台继续跑（因为没退出）
    }
}
