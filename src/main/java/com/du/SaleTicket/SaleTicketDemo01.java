package com.du.SaleTicket;

/**
 * 线程就是一个单独的资源类，没有任何的附属操作，降低耦合性
 * 属性方法
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        // 并发 多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        //@FunctionalInterface函数式接口，jdk1.8+可以使用lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}

/**
 * 资源类OOP
 */
class Ticket {
    // 属性、方法
    private int number = 10;

    //方法
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩下" + number + "票");
        }
    }
}