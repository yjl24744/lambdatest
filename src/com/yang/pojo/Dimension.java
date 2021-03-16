package com.yang.pojo;

/**
 * @author yangjianlei
 * @title: Dimensions
 * @projectName java8test
 * @description: TODO
 * @date 2021/3/2 15:53
 */
public class Dimension {
    private String code;
    private String name;
    private String value;

    public Dimension() {
    }

    public Dimension(String code, String name, String value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
