package com.du.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存！
 * 读-写 不能共存！
 * 写-写 不能共存！
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantCache myCache = new ReentrantCache();

        //写入
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp), String.valueOf(temp));
            }, String.valueOf(i)).start();
        }

        //读取
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }

    }
}

class ReentrantCache {
    private volatile Map<String, Object> map = new HashMap<>();

    //读写锁：更加细粒度的控制
    private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    //put，写只能有一个线程再写
    public void put(String key, Object value) {
        reentrantLock.writeLock().lock();

        try {
            System.out.println("写入->" + key);
            map.put(key, value);
            System.out.println("写入->" + key + " OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.writeLock().unlock();
        }

    }

    //get，读可以多个线程同时在读
    public void get(String key) {
        //加锁
        reentrantLock.readLock().lock();
        try {
            System.out.println("读取->" + key);
            map.get(key);
            System.out.println("读取->" + key + " OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            reentrantLock.readLock().unlock();
        }
    }

}

/*
 * 无锁的自定义缓存
 * */
class MyCache {
    private volatile Map<String, Object> map = new HashMap();

    //put
    public void put(String key, Object value) {
        System.out.println("写入->" + key);
        map.put(key, value);
        System.out.println("写入->" + key + " OK");
    }

    //get
    public void get(String key) {
        System.out.println("读取->" + key);
        map.get(key);
        System.out.println("读取->" + key + " OK");
    }

}