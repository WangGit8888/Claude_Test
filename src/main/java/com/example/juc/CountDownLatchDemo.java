package com.example.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器为4，表示需要等待4个线程
        CountDownLatch latch = new CountDownLatch(4);

        // 4个玩家线程
        for (int i = 1; i <= 4; i++) {
            int playerId = i;
            new Thread(() -> {
                try {
                    System.out.println("玩家" + playerId + " 正在准备...");
                    Thread.sleep((long) (Math.random() * 2000)); // 模拟准备时间
                    System.out.println("玩家" + playerId + " 准备就绪！");
                    latch.countDown(); // 计数器减1
                    System.out.println("玩家" + playerId + " 继续做自己的事（比如看装备）");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // 主线程（裁判）等待所有玩家就绪
        System.out.println("裁判等待所有玩家就绪...");
        latch.await(); // 阻塞，直到计数器变为0
        System.out.println("裁判：所有玩家已就绪，游戏开始！");
    }
}
