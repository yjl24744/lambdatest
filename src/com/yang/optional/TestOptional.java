package com.yang.optional;

import com.yang.pojo.Person;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yangjianlei
 * @title: TestOptional
 * @projectName java8test
 * @description: Optional 是为核心类库新设计的一个数据类型， 用来替换 null 值
 * @date 2020/12/2513:48
 *
 * Optional有2个好处：
 * 鼓励程序员记得检查变量是否为空，避免出bug
 * 第2点，也是最重点的一点，不用在跟null打交道，可以安心的避免npe
 */
public class TestOptional {

    public void test() {
        Optional a = Optional.of("a");
        System.out.println(a.get());
        Person person = new Person();
        // 此种情况报npe，用法不正确
        // Optional.of(person.getAge()).get();

        /**
         * 正确用法，一般是推荐用另外一个工厂方法ofNullable
         * 该方法可将一个空值转换成Optional对象，
         * 并且在调用get()方法之前，先使用isPresent检查Optional对象是否有值
         */
        Optional.ofNullable(person.getName()).ifPresent(System.out::println);
        Optional.ofNullable(person.getName()).ifPresentOrElse(System.out::println, () -> System.out.println("aaa"));

        Person person1 = new Person();
        person1.setName("yang");
        person1.setAge("18");
        person1.setGender("male");
        Optional.ofNullable(person1.getName()).ifPresentOrElse(System.out::println, () -> System.out.println("aaa"));

        Optional.ofNullable(person1.getName()).filter(v -> v.startsWith("y")).ifPresent(System.out::println);

    }

    public void test2(String key) {
    }

    public static void main(String[] args) {
        TestOptional optional = new TestOptional();
        optional.test2(null);
    }
}

