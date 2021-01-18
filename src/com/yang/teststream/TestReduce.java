package com.yang.teststream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangjianlei
 * @title: TestReduce
 * @projectName java8test
 * @description: TODO
 * @date 2021/1/18 18:38
 */
public class TestReduce {

    public static void main(String[] args) throws Exception{
        List<Foo> fooList = Lists.newArrayList(
                new Foo("A","san",1.0,2),
                new Foo("A","nas",13.0,1),
                new Foo("B","san",112.0,3),
                new Foo("C","san",43.0,5),
                new Foo("B","nas",77.0,7)
        );
        List<Bar> barList = Lists.newArrayList();
        fooList
                .stream()
                .collect(Collectors.groupingBy(Foo::getName,Collectors.toList()))
                .forEach((name,fooListByName)->{
                    Bar bar = new Bar();
                    bar = fooListByName
                            .stream()
                            .reduce(bar,(u,t)->u.sum(t),(u,t)->u);
                    System.out.println(bar.toString());
                    barList.add(bar);
                });
    }
}

class Foo {
    private String name;
    private String type;
    private Double typeValue;
    private Integer count;

    public Foo(String name, String type, Double typeValue, Integer count) {
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Double typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

class Bar{
    private String name;
    private Integer count;
    private Double totalTypeValue;
    private List<Baz> bazList;

    public Bar() {
        this.name = null;
        this.count = 0;
        this.totalTypeValue = 0.0;
        this.bazList = Lists.newArrayList();
    }

    public Bar sum(Foo foo){
        if(name == null){
            this.name = foo.getName();
        }
        this.count += foo.getCount();
        this.totalTypeValue += foo.getTypeValue();
        this.bazList.add(new Baz(foo.getType(),foo.getTypeValue()));
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name:").append(this.name).append(System.lineSeparator());
        sb.append("count:").append(this.count).append(System.lineSeparator());
        sb.append("totalTypeValue:").append(this.totalTypeValue).append(System.lineSeparator());
        sb.append("bazList:").append(System.lineSeparator());
        this.bazList.forEach(baz->{
            sb.append("\t").append("type:").append(baz.getType()).append(System.lineSeparator());
            sb.append("\t").append("typeValue:").append(baz.getTypeValue()).append(System.lineSeparator());
        });
        return sb.toString();
    }
}

class Baz {
    private String type;
    private Double typeValue;

    public Baz(String type, Double typeValue) {
        this.type = type;
        this.typeValue = typeValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Double typeValue) {
        this.typeValue = typeValue;
    }
}
