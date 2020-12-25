package com.yang.predicate;

import java.util.function.Predicate;

/**
 * @author yangjianlei
 * @title: TestPredicate
 * @projectName java8test
 * @description: TODO
 * @date 2020/12/2515:03
 */
public class TestPredicate {

    public void printBigValue(int value, Predicate<Integer> predicate) {
        if (predicate.test(value)) {
            System.out.println(value);
        }
    }

    public void printBigValueAnd(int value, Predicate<Integer> predicate) {
        if (predicate.and(v -> v < 8).test(value)) {
            System.out.println("value < 8 : " + value);
        }
    }
}
