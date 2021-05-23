package com.du.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest1 {

    //volatile 不保证原子性
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //不是一个原子性操作
        //num++;
        //AtomicInteger + 1 方法，CAS
        num.getAndIncrement();
    }

    public static void main(String[] args) {


        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        //main，gc线程默认一直执行
        while (Thread.activeCount() > 2) Thread.yield();

        System.out.println(Thread.currentThread().getName() + "---------->" + num);
    }
}