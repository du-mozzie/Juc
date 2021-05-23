package com.du.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ListTest {
    public static void main(String[] args) {
        /*
        *  并发下 ArrayList 不安全的，Synchronization
        *  java.util.ConcurrentModificationException并发修改异常，解决方案
        *  1、List<String> list = new Vector<>();
        *  2、List<String> list = Collections.synchronizedList(new ArrayList<>());
        *  3、List<String> list = new CopyOnWriteArrayList<>();
        * */

        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        /*  CopyOnWrite 写入时复制  COW  计算机程序设计领域的一种优化策略
        *   多个线程调用的时候，List，读取的时候固定的，写入(覆盖)
        *   再写入的时候避免覆盖，造成数据问题
        *   读写分离
        *   CopyOnWriteArrayList 使用的lock锁，Vector使用的synchronized关键字
        * */

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}