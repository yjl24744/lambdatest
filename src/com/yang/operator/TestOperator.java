package com.yang.operator;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author yangjianlei
 * @title: TestOperator
 * @projectName java8test
 * @description: TODO
 * @date 2020/12/2514:52
 */
public class TestOperator {

    public static void main(String[] args) {
        UnaryOperator<Integer> add = x -> x + 1;
        System.out.println(add.apply(1));

        BinaryOperator<Integer> add2 = (x, y) -> x + y;
        System.out.println(add2.apply(100, 101));

        BinaryOperator<Integer> min = BinaryOperator.minBy((o1, o2) -> o1 - o2);
        System.out.println(min.apply(100, 102));
        BinaryOperator<Integer> max = BinaryOperator.maxBy((o1, o2) -> o1 - o2);
        System.out.println(max.apply(103, 104));


    }
}
