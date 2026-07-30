package com.example.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 初始化屏障为4，表示需要4个线程都到达，到达后执行 Runnable 动作
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("====== 所有玩家已到齐，一起出发执行任务！ ======");
        });

        // 4个玩家线程
        for (int i = 1; i <= 4; i++) {
            int playerId = i;
            new Thread(() -> {
                try {
                    // 第一轮集结
                    System.out.println("玩家" + playerId + " 到达城门口");
                    barrier.await(); // 等待其他玩家
                    System.out.println("玩家" + playerId + " 开始做任务...");
                    Thread.sleep(1000);
                    System.out.println("玩家" + playerId + " 任务完成，返回城门口");

                    // 第二轮集结（屏障可重复使用）
                    barrier.await(); // 再次等待
                    System.out.println("玩家" + playerId + " 开始第二轮任务...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
