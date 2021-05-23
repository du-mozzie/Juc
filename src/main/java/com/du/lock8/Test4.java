package com.du.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，关于锁的八个问题
 * 7、一个静态同步方法，一个普通同步方法，一个对象       1/打电话  2/发短信
 * 8、一个静态同步方法，一个普通同步方法，两个对象       1/打电话  2/发短信
 */
public class Test4 {
    public static void main(String[] args) {
        //两个对象，两个调用者，两把锁！
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        // 锁的存在
        new Thread(() -> phone1.sendSms(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.call(), "B").start();
    }
}

class Phone4 {

    // 静态同步方法
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    // 普通同步方法
    public synchronized void call() {
        System.out.println("打电话");
    }

}