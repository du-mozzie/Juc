package com.du.SaleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程就是一个单独的资源类，没有任何的附属操作，降低耦合性
 * 属性方法
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {

        // 并发 多个线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket2 = new Ticket2();

        //@FunctionalInterface函数式接口，jdk1.8+可以使用lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "C").start();
    }

}

/**
 * Lock锁
 */
class Ticket2 {
    // 属性、方法
    private int number = 10;

    Lock lock = new ReentrantLock();

    //方法
    public synchronized void sale() {
        //加锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩下" + number + "票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}