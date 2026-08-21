package com.example.juc.zxsx;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("A"));
        CompletableFuture<Void> future2 = future1.thenRunAsync(() -> System.out.println("B"));
        CompletableFuture<Void> future3 = future2.thenRunAsync(() -> System.out.println("C"));

        // 阻塞等待所有任务完成
        future3.join();
    }
}