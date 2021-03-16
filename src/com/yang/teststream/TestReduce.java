package com.yang.teststream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        TestReduce testReduce = new TestReduce();
        // testReduce.testNonMatch();
        // testReduce.testFlagMap();
        testReduce.testReduce2();
    }

    /**
     * 求两个集合的差集
     */
    private void testNonMatch() {
        List<Foo> foos = Lists.newArrayList(
                new Foo("yang", "person", 16.0, 1),
                new Foo("aaa", "monkey", 14.0, 1),
                new Foo("du", "person", 16.0, 1)
        );
        List<Foo> foos2 = Lists.newArrayList(
                new Foo("yang", "person", 16.0, 1),
                new Foo("bbb", "person", 16.0, 1),
                new Foo("aaa", "monkey", 14.0, 1)
        );
        List<Foo> difference = foos.stream()
                .filter(dimensionPO -> foos2.stream()
                        .map(Foo::getName)
                        .noneMatch(name -> Objects.equals(dimensionPO.getName(), name)))
                .collect(Collectors.toList());
        difference.forEach(System.out::println);
    }

    /**
     * 测试reduce使用
     */
    private void testReduce() {
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

    /**
     * 测试reduce使用
     */
    private void testReduce2() {
        List<Foo> fooList = Lists.newArrayList(
                new Foo("A","san1",1.0,2),
                new Foo("A","nas2",13.0,1),
                new Foo("A","nas3",13.0,1),
                new Foo("B","san1",112.0,3),
                new Foo("C","san1",43.0,5),
                new Foo("B","nas2",77.0,7)
        );
        List<Bar> barList = Lists.newArrayList();
        fooList
                .stream()
                .collect(Collectors.groupingBy(Foo::getName,Collectors.toList()))
                .forEach((name,fooListByName)->{
                    Bar bar = new Bar();
                    bar = fooListByName
                            .stream()
                            .reduce(bar,(u,t)->toBar(t, u),(u,t)->u);
                    System.out.println(bar.toString());
                    barList.add(bar);
                });
    }

    private Bar toBar(Foo foo, Bar bar) {
        if(bar.getName() == null){
            bar.setName(foo.getName());
        }
        if(bar.getName2() == null){
            bar.setName2(foo.getType());
        } else {
            bar.setName2(bar.getName2()+","+foo.getType());
        }

        return bar;
    }

    private void testFlagMap() {
        List<Student> bazs = Lists.newArrayList(
                new Student("yang", "11", "man"),
                new Student("jian", "12", "man"),
                new Student("lei", "13", "man")
        );
        List<Student> bazs2 = Lists.newArrayList(
                new Student("li", "11", "man"),
                new Student("yao", "12", "man"),
                new Student("fei", "13", "man")
        );
        List<ClassRoom> rooms = Lists.newArrayList(
                new ClassRoom("三年一班", "中国", bazs),
                new ClassRoom("三年二班", "美国", bazs2)
        );
        List<String> names = rooms.stream().flatMap(classRoom -> classRoom.getStudents().stream().map(Student::getName)).collect(Collectors.toList());
        names.forEach(System.out::println);
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

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", typeValue=" + typeValue +
                ", count=" + count +
                '}';
    }
}

class Bar{
    private String name;
    private Integer count;
    private Double totalTypeValue;
    private List<Baz> bazList;
    private String name2;

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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name:").append(this.name).append(System.lineSeparator());
        sb.append("name2:").append(this.name2).append(System.lineSeparator());
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

class ClassRoom {
    private String name;
    private String location;
    private List<Student> students;

    public ClassRoom() {
    }

    public ClassRoom(String name, String location, List<Student> students) {
        this.name = name;
        this.location = location;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", students=" + students +
                '}';
    }
}

class Student {
    private String name;
    private String age;
    private String gender;

    public Student() {
    }

    public Student(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}