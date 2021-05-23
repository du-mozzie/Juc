package com.du.ProducerConsumer;

public class Test1 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decremnet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decremnet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class Data{
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (number != 0){
            //等待
            this.wait();
        }
        //代码执行
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" +number);
        //唤醒其他线程
        this.notifyAll();
    }

    //-1
    public synchronized void decremnet() throws InterruptedException {
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" +number);
        this.notifyAll();
        this.notify();
    }
}