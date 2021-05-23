package com.du.pool;

import java.util.concurrent.*;

public class Demo02 {
    public static void main(String[] args) {
        //最大线程到底该如何定义
        //1、CPU 密集型，几核，就是几，可以保持CPu的效率最高！
        //2、IO 密集型 >判断你程序中十分耗IO的线程，
        //程序 15 个大型任务 io十分占用资源！
        ExecutorService executor = new ThreadPoolExecutor(
                2,
                //获取CPU的核数
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 1; i <= 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " OK");
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}