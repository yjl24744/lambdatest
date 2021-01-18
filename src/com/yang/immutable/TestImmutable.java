package com.yang.immutable;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangjianlei
 * @title: TestImmutable
 * @projectName java8test
 * @description: TODO
 * @date 2021/1/18 18:20
 */
public class TestImmutable {
    public static void main(String[] args) {
        TestImmutable testImmutable = new TestImmutable();
        testImmutable.testList();
        testImmutable.testImmutableList();
        testImmutable.testImmutableList2();
    }

    public void testList() {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        List<String> readOnlyList = Collections.unmodifiableList(list);
        list.add("c");
        System.out.println("------------------ testList --------------------");
        readOnlyList.forEach(System.out::println);
    }

    public void testImmutableList() {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        List<String> readOnlyList = ImmutableList.copyOf(list);
        list.add("c");
        System.out.println("------------------ testImmutableList --------------------");
        readOnlyList.forEach(System.out::println);
    }

    public void testImmutableList2() {
        List<String> readOnlyList = ImmutableList.of("a", "b");
        System.out.println("------------------ testImmutableList2 --------------------");
        readOnlyList.forEach(System.out::println);
    }

}
