package com.du.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    // AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> atomic = new AtomicStampedReference(1, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            // 获得版本号
            System.out.println(Thread.currentThread().getName() + "------1>" + atomic.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomic.compareAndSet(1, 2, atomic.getStamp(), atomic.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "------2>" + atomic.getStamp());

            atomic.compareAndSet(2, 1, atomic.getStamp(), atomic.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "------3>" + atomic.getStamp());
            System.out.println();

        }, "A").start();

        // 乐观锁的原理相同
        new Thread(() -> {
            // 获得版本号
            int stamp = atomic.getStamp();
            System.out.println(Thread.currentThread().getName() + "------1>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomic.compareAndSet(1, 6, stamp, atomic.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "------2>" + atomic.getStamp());

        }, "B").start();

    }
}