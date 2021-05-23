package com.du.function;

import java.util.function.Consumer;

/**
* 消费型接口 consumer，只有输入，没有返回值
*/
public class Demo03 {
    public static void main(String[] args) {
        Consumer consumer = (Consumer<String>) o -> System.out.println(o);

        consumer.accept("abc");
    }
}