package com.yang.flatmap;

import com.google.common.collect.Lists;
import com.yang.pojo.Dimension;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangjianlei
 * @title: TestDecartesList
 * @projectName java8test
 * @description: TODO
 * @date 2021/3/2 15:56
 */
public class TestDecartesList {
    public static void main(String[] args) {
        TestDecartesList.descartesToMap();
    }

    public static List descartes(List... lists) {
        List<Dimension> dimensions = Lists.newArrayList(
                new Dimension("PF_ID", "组合ID", "10001,10002,10003"),
                new Dimension("DATE_START", "开始时间", "20200101,20200102")
        );
        List tempList = new ArrayList<>();

        for (List list : lists) {
            if (tempList.isEmpty()) {
                tempList = list;
            } else {
                tempList = (List) tempList.stream().flatMap(item -> list.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
            }

        }

        return tempList;

    }

    public static void descartesToMap() {
        List<Dimension> dimensions = Lists.newArrayList(
                new Dimension("PF_ID", "组合ID", "10001,10002,10003"),
                new Dimension("DATE_START", "开始时间", "20200102,20200101"),
                new Dimension("DATE_END", "结束时间", "20200102")
        );
        Map<String, List<String>> pf = dimensions.stream()
                .collect(Collectors.toMap(Dimension::getCode,
                        dimension -> Arrays.asList(dimension.getValue().split(",")),
                        (n1, n2) -> n1,
                        LinkedHashMap::new));
        /*List<Map<String, String>> pf2 = dimensions.stream()
                .map(dimension -> {
                    Arrays.stream(dimension.getValue().split(",")).map(s -> {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put(dimension.getCode(), s);
                        return map;
                    });
                })
                .collect(Collectors.toList());*/
        List<Map<String, String>> list = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : pf.entrySet()) {
            String key = entry.getKey();
            List<String> valueList = entry.getValue();
            if (list.isEmpty()) {
                list = valueList.stream().map(s -> {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put(key, s);
                    return map;
                }).collect(Collectors.toList());
            } else {
                list = list.stream().flatMap(item -> valueList.stream()
                        .map(item2 -> {
                            item.put(key, item2);
                            return item;
                        }))
                        .collect(Collectors.toList());
            }
        }
        System.out.println("hello");
    }

    public static void testChangeList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "a");
        map.put("2", "a");
        map.put("3", "a");
        map.put("4", "a");
        list.add(map);
        Map<String, String> map2 = new LinkedHashMap<>();
        map2.put("1", "b");
        map2.put("2", "b");
        map2.put("3", "b");
        map2.put("4", "b");
        list.add(map2);
        Map<String, String> map3 = new LinkedHashMap<>();
        map2.put("1", "b");
        map2.put("2", "b");
        map2.put("3", "b");
        map2.put("4", "b");
    }
}
