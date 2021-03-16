package com.yang.map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yang.pojo.Person;

import java.util.*;

/**
 * @author yangjianlei
 * @title: TestMap
 * @projectName java8test
 * @description: TODO
 * @date 2021/1/24 14:26
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String, String> columnAliasMap = new LinkedHashMap<>();
        columnAliasMap.put("a.yang", "yang");
        columnAliasMap.put("b.yang", "C1");
        columnAliasMap.put("a.duan", "duan");
        columnAliasMap.put("a.zheng", "zheng");
        List<Person> personList = ImmutableList.of(
                new Person("yang", "1", "1", ""),
                new Person("yang", "", "", ""),
                new Person("duan", "", "", "")
        );
        columnAliasMap.forEach((k, v) -> {
            System.out.println(k + "====" + v);
        });
        List<Person> people = new ArrayList<>();
        for (Person person : personList) {
            Iterator<Map.Entry<String, String>> iterator = columnAliasMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                if (key.substring(key.indexOf(".")+1).equalsIgnoreCase(person.getName())) {
                    // 别名即视图字段名
                    person.setAlias(entry.getValue());
                    iterator.remove();
                    break;
                }
            }
            people.add(person);
        }
        people.forEach(System.out::println);

        System.out.println("===============================");
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", -100);
        System.out.println("-100".equals(map.get("c").toString()));

    }

    private Map<String, String> map = ImmutableMap.of("a","b", "a", "c");
}

