package com.yang.immutable;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.yang.utils.Constants;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author yangjianlei
 * @title: TestImmutable
 * @projectName java8test
 * @description: TODO
 * @date 2021/1/18 18:20
 */
public class TestImmutable {
    public static void main(String[] args) {
        /*TestImmutable testImmutable = new TestImmutable();
        testImmutable.testList();
        testImmutable.testImmutableList();
        testImmutable.testImmutableList2();*/
        String keyword = "describe";
        String sql = "select describeaaa from abc from aa";
        String reg = sql.substring(sql.lastIndexOf("select"), sql.indexOf("from"));
        System.out.println(reg.replaceAll(keyword, "\"" + keyword + "\""));
        System.out.println(sql);
        System.out.println("==============================");
        Map<String, String> map = Map.of(keyword,"\"" + keyword + "\"");

        System.out.println(map.keySet().stream()
                .reduce(sql, (s, k) -> s.replaceAll(k, map.get(k)))
                .toString());

        System.out.println("==============================");
        System.out.println(CharMatcher.anyOf("describe").replaceFrom(keyword, "\"" + keyword + "\""));

        System.out.println("==============================");
        String str = "12312,agg  ";
        CharMatcher charMatcher1 = CharMatcher.is('1');
        CharMatcher charMatcher2 = CharMatcher.is('2');
        //两个CharMatcher或操作
        CharMatcher charMatcher3 = charMatcher1.or(charMatcher2);
        System.out.println(charMatcher3.retainFrom(str));

        System.out.println("==============================");
        CharMatcher charMatcher4 = CharMatcher.anyOf("agg");
        CharMatcher charMatcher5 = CharMatcher.anyOf("agg");
        //两个CharMatcher或操作
        System.out.println(charMatcher4.replaceFrom(str, "aaa"));

        System.out.println("==============================");
        System.out.println(Joiner.on(",").join("1", "2","3"));

        System.out.println("==============================");
        Constants.map.forEach((k ,v) -> {
            System.out.println(k + "=====" + v);
        });
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
