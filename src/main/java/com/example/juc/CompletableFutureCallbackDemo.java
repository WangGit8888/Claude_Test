package com.example.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 无返回值 + 回调通知(thenRun / thenAccept)
 */
public class CompletableFutureCallbackDemo {
    public static void main(String[] args) throws Exception {
        // 烧水（无返回值）
        CompletableFuture<Void> boilFuture = CompletableFuture.runAsync(() -> {
            System.out.println("烧水中...");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {}
            System.out.println("水烧开了！");
        });

        // ✅ 烧水完成后，执行后续动作（不需要前一步的结果）
        boilFuture.thenRun(() -> {
            System.out.println("📢 通知：水烧开了，可以泡茶了！");
        });

        // ✅ 或者用 thenAccept（虽然 Void 没有实际值，但语法上可以用）
        // boilFuture.thenAccept(v -> System.out.println("水烧开了"));

        // 让主线程等待一会儿，观察回调执行
        TimeUnit.SECONDS.sleep(3);
    }
}
