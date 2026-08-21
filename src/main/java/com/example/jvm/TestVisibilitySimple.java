package com.example.jvm;

public class TestVisibilitySimple {
    static Integer a = 0;  // 加volatile保证可见性
    static long readCount = 0;       // 改成 long，避免溢出

    public static void main(String[] args) throws InterruptedException {
        // 线程A：每500ms修改一次
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a += 1;
                System.out.println("【写】a = " + a);
            }
        }).start();

        // 线程B：疯狂读取
        new Thread(() -> {
            while (true) {
                int value = a;
                readCount++;
                // 改成每100万次打印一次，减少输出
                if (readCount % 1_000_000 == 0) {
                    System.out.println("【读】第" + readCount + "次, a = " + value);
                }
            }
        }).start();

        Thread.sleep(10000);
        System.out.println("\n========== 10秒观察结束 ==========");
        System.exit(0);
    }
}