package com.example.juc;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // 轮询检查中断标志
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("子线程正在运行...");
                try {
                    Thread.sleep(500); // 模拟耗时操作
                } catch (InterruptedException e) {
                    // 阻塞方法被中断时，会清除中断标志位
                    System.out.println("子线程被中断，退出循环");
                    // 注意：此时 isInterrupted() 为 false
                    // 如果不想退出，可以重新设置中断标志：Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("子线程正常退出");
        });

        t1.start();

        // 主线程等待2秒后中断子线程
        Thread.sleep(2000);
        t1.interrupt();
        System.out.println("主线程已发送中断信号");

        t1.join();
        System.out.println("程序结束");
    }
}