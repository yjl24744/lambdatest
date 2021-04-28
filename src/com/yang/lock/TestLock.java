package com.yang.lock;/**
 * @title: TestLock
 * @projectName java8test
 * @description: TODO
 * @author yangjianlei
 * @date 2021/3/23 18:18
 */

import com.yang.pojo.Person;

/**
 * @ClassName TestLock
 * @Description: TODO
 * @Author yjl
 * @Date 2021/3/23 
 * @Version V1.0
 */
public class TestLock {
    public static Person person = new Person();

    public static void main(String[] args) throws InterruptedException {
        person.setAlias("1");
        int i = 1;
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(()->{
            try {
                phone.add(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                phone2.add(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
