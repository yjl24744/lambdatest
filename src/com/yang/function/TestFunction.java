package com.yang.function;

import java.util.function.Function;

/**
 * @author yangjianlei
 * @title: TestFunction
 * @projectName java8test
 * @description: Function表示一个接受一个参数并产生结果的函数
 * @date 2020/12/2514:26
 *
 * Function是函数的意思，用来定义一个抽象函数，接收泛型T对象，返回泛型R对象。
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
