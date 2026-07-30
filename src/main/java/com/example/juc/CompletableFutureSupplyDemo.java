package com.example.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 有返回值(supplyAsync)
 */
public class CompletableFutureSupplyDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始：" + Thread.currentThread().getName());

        // ✅ 有返回值的异步任务：烧水
        CompletableFuture<String> boilFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("  烧水线程开始：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3); // 模拟烧水耗时 3 秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("  烧水完成！");
            return "🔥 100°C 开水";
        });

        // ✅ 有返回值的异步任务：洗水果（和烧水并行）
        CompletableFuture<String> fruitFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("  洗水果线程开始：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "🍎 洗好的苹果";
        });

        // ✅ 组合两个结果：都完成后泡茶
        CompletableFuture<String> teaFuture = boilFuture
                .thenCombine(fruitFuture, (boilWater, fruit) -> {
                    System.out.println("  泡茶线程：" + Thread.currentThread().getName());
                    return "泡好的茶（" + boilWater + " + " + fruit + "）";
                });

        // ✅ 阻塞等待最终结果
        String result = teaFuture.get();
        System.out.println("✅ 最终结果：" + result);

        System.out.println("主线程结束");
    }
}
