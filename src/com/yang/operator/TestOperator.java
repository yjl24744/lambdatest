package com.yang.operator;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author yangjianlei
 * @title: TestOperator
 * @projectName java8test
 * @description: UnaryOperator表示对单个操作数的操作，该操作产生与其操作数类型相同的结果
 * @date 2020/12/2514:52
 *
 * UnaryOperator是一元操作符的意思，接收一个泛型T对象参数，返回相同T类型对象。
 * BinaryOperator是二元操作符的意思，接收两个相同泛型参数类型T，返回R类型对象。
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
