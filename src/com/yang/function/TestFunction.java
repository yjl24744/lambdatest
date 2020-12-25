package com.yang.function;

import java.util.function.Function;

/**
 * @author yangjianlei
 * @title: TestFunction
 * @projectName java8test
 * @description: TODO
 * @date 2020/12/2514:26
 */
public class TestFunction {

    public static void main(String[] args) {
        new TestFunction().test();
    }

    public void test() {
        Function<Integer, Integer> addMethod = x -> x + 1;
        Function<Integer, Integer> multiMethod = x -> x * 2;

        int x = 2;
        System.out.println("f(x) = x + 1, when x = " + x + ", then f(x) = " + addMethod.apply(x));
        System.out.println("f(x) = x + 1, g(x) = 2x, when x = " + x + ", then f(g(x)) = " + addMethod.compose(multiMethod).apply(x));
        System.out.println("f(x) = x + 1, g(x) = 2x, when x = " + x + ", then g(f(x)) = " + addMethod.andThen(multiMethod).apply(x));
        System.out.println("compose vs andThen : f(g(x)) = " + addMethod.compose(multiMethod).apply(x) + ", " + multiMethod.andThen(addMethod).apply(x));
    }
}
