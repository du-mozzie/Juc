package com.du.commonly_used_class;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6,必须要执行的任务的时候，再使用
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go Out");
                //倒计时-1
                count.countDown();
            }, String.valueOf(i)).start();
        }

        //等待计数器归0
        count.await();
        System.out.println("Close Door");

    }
}