package com.du.function;

import java.util.function.Supplier;

/**
 * 供给型接口 supplier，没有输入值，只有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        //Supplier supplier = new Supplier<Integer>() {
        //    @Override
        //    public Integer get() {
        //        System.out.println("进入get()");
        //        return 1024;
        //    }
        //};

        Supplier supplier = ()->{
            System.out.println("get()");
            return 1024;
        };

        System.out.println(supplier.get());

    }
}