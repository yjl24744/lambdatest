package com.yang.pojo;

public class Person {
    private String name;
    private String age;
    private String gender;
    private String alias;

    public Person() {
    }

    public Person(String name, String age, String gender, String alias) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}