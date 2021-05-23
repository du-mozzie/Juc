package com.du.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，关于锁的八个问题
 * 3、增加一个普通方法，先执行哪个   1/普通方法 2/发短信
 * 4、两个方法，两个同步方法   先发短信还是打电话    //打电话
 */
public class Test2 {
    public static void main(String[] args) {
        //两个对象，两个调用者，两把锁！
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        // 锁的存在
        new Thread(() -> phone1.sendSms(),"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //new Thread(() -> phone.hello(),"B").start();
        new Thread(() -> phone2.call(),"B").start();


    }
}

class Phone2{

    //synchronized 锁的对象是方法的调用者
    //两个方法是同一个锁，谁先拿到谁先执行

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }

    // 这里没有锁！不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("say hello");
    }
}