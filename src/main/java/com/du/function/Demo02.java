package com.du.function;

import java.util.function.Predicate;

public class Demo02 {
    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };*/

        Predicate<String> predicate = (str) -> str.isEmpty();
        System.out.println(predicate.test(""));
    }
}