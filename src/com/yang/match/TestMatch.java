package com.yang.match;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.yang.pojo.ServiceTag;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangjianlei
 * @title: TestMatch
 * @projectName java8test
 * @description: TODO
 * @date 2021/2/8 11:26
 */
public class TestMatch {
    public static void main(String[] args) {
        TestMatch testMatch = new TestMatch();
        testMatch.getTagList().forEach(System.out::println);
    }

    private List<ServiceTag> getTagList() {
            // 差集
        List<ServiceTag> oldList = ImmutableList.of(
                new ServiceTag(1L, 1L, 1L),
                new ServiceTag(2L, 1L, 2L),
                new ServiceTag(3L, 1L, 3L),
                new ServiceTag(4L, 2L, 1L),
                new ServiceTag(5L, 2L, 2L)
        );
        List<ServiceTag> tagList = ImmutableList.of(
                new ServiceTag(1L, 1L, 1L),
                new ServiceTag(2L, 1L, 2L),
                new ServiceTag(3L, 1L, 3L),
                new ServiceTag(4L, 2L, 1L),
                new ServiceTag(5L, 2L, 2L),
                new ServiceTag(1L, 3L, 1L),
                new ServiceTag(2L, 2L, 4L)
        );
        List<ServiceTag> tagList2 = tagList.stream()
                    .filter(tagPO -> oldList.stream()
                            .noneMatch(oldTag -> {
                                if (Objects.equal(oldTag.getTagId(), tagPO.getTagId())
                                        && Objects.equal(oldTag.getServiceId(), tagPO.getServiceId())) {
                                    return true;
                                }
                                return false;
                            })
                    )
                    .collect(Collectors.toList());
        return tagList2;
    }
}
