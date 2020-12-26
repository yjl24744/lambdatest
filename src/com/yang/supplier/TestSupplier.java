package com.yang.supplier;

import java.util.function.Supplier;

/**
 * Supplier代表结果的提供者
 * Supplier是提供者的意思，用来生成泛型T对象。
 */
public class TestSupplier {

    public void test() {
        Supplier supplier = () -> "Hello Java8 supplier";
        Supplier<String> supplier1 = () -> "hello world";
        System.out.println(supplier.get());
    }

    public static void main(String[] args) {
        TestSupplier supplier = new TestSupplier();
        supplier.test();
    }
}
