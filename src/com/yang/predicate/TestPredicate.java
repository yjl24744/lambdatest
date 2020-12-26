package com.yang.predicate;

import java.util.function.Predicate;

/**
 * @author yangjianlei
 * @title: TestPredicate
 * @projectName java8test
 * @description: Predicate表示一个参数的谓词（布尔值函数）
 * @date 2020/12/2515:03
 *
 * Predicate是谓词的意思，用来判断泛型T对象是否符合条件，如果符合返回true，否则返回false。
 */
public class TestPredicate {

    /**
     * Predicate是谓词的意思，用来判断泛型T对象是否符合条件
     * 如果符合返回true，否则返回false
     * test方法用来判断，返回boolean
     */
    public void printBigValue(int value, Predicate<Integer> predicate) {
        if (predicate.test(value)) {
            System.out.println(value);
        }
    }

    /**
     * 先判断and里面的v < 8的判断
     * 如果v < 8正确，则继续判断predicate
     * 上述先后关系相反。。。
     */
    public void printBigValueAnd(int value, Predicate<Integer> predicate) {
        if (predicate.and(v -> v < 8).test(value)) {
            System.out.println("value < 8 : " + value);
        }
    }

    /**
     * 看源码
     */
    public void testOr(int value, Predicate<Integer> predicate) {
        if (predicate.or(v -> v > 8).test(value)) {
            System.out.println("test or : " + true);
        }
    }

    /**
     * Predicate的否定用法
     */
    public void testNegate(String value) {
        Predicate<String> predicate1 = str -> str.startsWith("a");
        Predicate<String> predicate2 = str -> str.length() < 4;

        boolean result1 = predicate1.negate().test(value);
        System.out.println("test nagate, result1 is : " + result1);
        boolean result2 = predicate1.and(predicate2.negate()).test(value);
        System.out.println("test nagate, result2 is : " + result2);
    }

    /**
     * Predicate的对象是否相等判断
     */
    public void testIsEqual(String value) {
        boolean result = Predicate.isEqual(value).test(value);
    }

    public static void main(String[] args) {
        TestPredicate predicate = new TestPredicate();
        predicate.printBigValue(10, v -> v >= 10);
        predicate.printBigValueAnd(10, v -> v >= 10);
        predicate.testOr(10, v -> v <= 8);
        predicate.testNegate("abc");
    }
}
