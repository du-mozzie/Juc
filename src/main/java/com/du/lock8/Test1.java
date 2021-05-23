package com.du.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，关于锁的八个问题
 * 1、标准情况下，两个线程谁先执行 发短信，打电话         1/发短信 2/打电话
 * 2、sendSms延迟4秒下，两个线程谁先执行 发短信，打电话         1/发短信 2/打电话
 * */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        // 锁的存在
        new Thread(() -> phone.sendSms(),"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> phone.call(),"B").start();


    }
}

class Phone{

    //synchronized 锁的对象是方法的调用者
    //两个方法是同一个锁，谁先拿到谁先执行

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}