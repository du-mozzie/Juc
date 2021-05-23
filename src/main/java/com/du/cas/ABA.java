package com.du.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class ABA {
    public static void main(String[] args) {

        // 使用乐观锁
        // 总是认为值没有改变过
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 异常线程
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2021, 2020));
        System.out.println(atomicInteger.get());

        // 正确的线程
        System.out.println(atomicInteger.compareAndSet(2020, 6666));
        System.out.println(atomicInteger.get());

    }
}