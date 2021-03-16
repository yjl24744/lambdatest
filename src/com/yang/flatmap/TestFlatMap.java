package com.yang.flatmap;

import com.google.common.collect.ImmutableList;
import com.yang.pojo.ServiceTag;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yangjianlei
 * @title: TestFlatMap
 * @projectName java8test
 * @description: TODO
 * @date 2021/2/8 10:50
 */
public class TestFlatMap {
    public static void main(String[] args) {
        TestFlatMap testFlatMap = new TestFlatMap();
        List<Long> serviceIds = ImmutableList.of(1L, 2L, 3L);
        List<Long> tagList = null;
        List<ServiceTag> list = testFlatMap.getTagList(serviceIds, tagList);
        System.out.println(list==null);
    }

    private List<ServiceTag> getTagList(List<Long> serviceIds, List<Long> tagIds) {
        return serviceIds.stream()
                .flatMap(serviceId -> tagIds.stream()
                        .map(tagId -> {
                            ServiceTag serviceTagPO = new ServiceTag();
                            serviceTagPO.setServiceId(serviceId);
                            serviceTagPO.setTagId(tagId);
                            return serviceTagPO;
                        })
                )
                .collect(Collectors.toList());
    }
}
