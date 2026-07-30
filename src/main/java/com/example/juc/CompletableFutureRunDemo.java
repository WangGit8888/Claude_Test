package com.example.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 无返回值(runAsync)
 */
public class CompletableFutureRunDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始：" + Thread.currentThread().getName());

        // ❌ 无返回值的异步任务：洗茶壶、洗茶叶（纯动作）
        CompletableFuture<Void> prepareFuture = CompletableFuture.runAsync(() -> {
            System.out.println("  准备茶具线程开始：" + Thread.currentThread().getName());
            try {
                System.out.println("  正在洗茶壶...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("  正在洗茶叶...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("  茶具准备完成！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // ❌ 无返回值的异步任务：烧水（但烧水本身可以没有返回值，只做动作）
        CompletableFuture<Void> boilFuture = CompletableFuture.runAsync(() -> {
            System.out.println("  烧水线程开始：" + Thread.currentThread().getName());
            try {
                System.out.println("  正在烧水...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("  水烧开了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // ✅ 等待所有无返回值的任务全部完成
        CompletableFuture<Void> allDone = CompletableFuture.allOf(prepareFuture, boilFuture);

        // 注意：allOf 返回的是 CompletableFuture<Void>，本身没有结果
        // 阻塞等待全部完成
        allDone.join();  // 或者 get()

        System.out.println("✅ 所有准备工作完成，开始泡茶！");
        System.out.println("主线程结束");
    }
}
