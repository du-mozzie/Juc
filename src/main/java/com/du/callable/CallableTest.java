package com.du.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new Runnable()).start();
        //new Thread(new FutureTask<v>().start();
        //new Thread(new FutureTask<MyCallable>(new MyCallable())).start();
        MyCallable callable = new MyCallable();
        FutureTask task = new FutureTask(callable);

        new Thread(task).start();
        //结果会被缓存，效率高
        new Thread(task).start();

        //这个get 方法可能会产生阻塞！把他放到最后，或者使用异步通信来处理
        Integer num = (Integer) task.get();

        System.out.println(num);
    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}