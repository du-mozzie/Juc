package com.du.volatileTest;

import java.util.concurrent.TimeUnit;

public class JmmDemo1 {

    //不加volatile程序会死循环
    private volatile static int num = 0;

    //main线程
    public static void main(String[] args) {

        //线程1对主内存的变化不可见，需要添加volatile
        new Thread(()->{
           while (num==0);
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);

    }
}