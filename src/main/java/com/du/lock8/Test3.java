package com.du.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，关于锁的八个问题
 * 5、增加两个静态方法哪个先执行       1/发短信  2/打电话
 * 6、两个对象!增加两个静态的同步方法。      1/发短信   2/打电话
 */
public class Test3 {
    public static void main(String[] args) {
        //两个对象，两个调用者，两把锁！
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        // 锁的存在
        new Thread(() -> phone1.sendSms(),"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.call(),"B").start();
    }
}

class Phone3{

    //synchronized 锁的对象是方法的调用者
    // static静态方法
    // 类一加载就有了！锁的是class
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }

}