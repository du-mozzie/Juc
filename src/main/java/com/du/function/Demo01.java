package com.du.function;

import java.util.function.Function;

public class Demo01 {
    public static void main(String[] args) {
        /*Function function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };*/

        Function<String, String> function = (str) -> str;

        System.out.println(function.apply("abc"));
    }
}