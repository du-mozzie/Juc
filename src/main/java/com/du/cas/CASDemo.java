package com.du.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    // CAS  compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        // 期望 更新 达到期望的值就更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果期望值达到了就更新，否则不更新，CAS 是CPU的并发原语
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020, 2022));
        System.out.println(atomicInteger.get());
    }
}