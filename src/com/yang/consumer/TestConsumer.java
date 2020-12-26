package com.yang.consumer;

import java.util.function.Consumer;

/**
 * Consumer表示一个接受单个输入参数且不返回结果的操作
 * Consumer是消费者的意思，用来接收一个泛型T对象，执行相关操作，最后返回void。
 */
public class TestConsumer {

    /**
     * accept接收value，并进行操作
     */
    public void test(String value) {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept(value);
    }

    /**
     * 测试andThen方法
     * 先进行输出value，
     * 然后截取value前两位
     */
    public void testAndThen(String value) {
        Consumer<String> consumer1 = s -> System.out.println(s);
        Consumer<String> consumer2 = s -> System.out.println(s.substring(0, 2));
        consumer1.andThen(consumer2).accept(value);
    }

    public static void main(String[] args) {
        TestConsumer consumer = new TestConsumer();
        consumer.test("yang");
        consumer.testAndThen("yangjianlei");
    }
}
