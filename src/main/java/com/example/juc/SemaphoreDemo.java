package com.example.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        // 3个许可，表示最多同时 3 个线程执行
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 5; i++) {
            int carId = i;
            new Thread(() -> {
                try {
                    System.out.println("🚗 车辆" + carId + " 到达，尝试进入停车场...");
                    semaphore.acquire(); // 申请许可，没有则阻塞
                    System.out.println("✅ 车辆" + carId + " 进入停车场（剩余车位: " + semaphore.availablePermits() + "）");

                    // 模拟停车时间
                    int parkTime = (int) (Math.random() * 3000);
                    Thread.sleep(parkTime);
                    System.out.println("🔄 车辆" + carId + " 停车 " + parkTime + "ms 后离开");

                    semaphore.release(); // 释放许可
                    System.out.println("🚀 车辆" + carId + " 已离开，释放一个车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}