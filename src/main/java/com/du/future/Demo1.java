package com.du.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用： CompletableFuture
 * // 异步执行
 * // 成功回调
 * // 失败回调
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* 没有返回值的 runAsync 异步回调
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "=>void");
        });

        System.out.println("Hello");

        //获取阻塞执行结果
        future.get();*/

        //有返回值的 supplyAsync 异步回调
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 1024;
        });

        System.out.println(future.whenComplete((t, u) -> {
            // 正常的返回结果
            System.out.println("t=>" + t);
            // 异常信息
            System.out.println("u=>" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            //可以获取到错误的返回结果
            return -1;
        }).get());
        System.out.println("abc".contentEquals("a"));
    }
}