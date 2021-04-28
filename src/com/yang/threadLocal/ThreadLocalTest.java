package com.yang.threadLocal;

import com.yang.pojo.Person;

/**
 * @ClassName ThreadLocalTest
 * @Description: TODO
 * @Author yjl
 * @Date 2021/4/20 
 * @Version V1.0
 */
public class ThreadLocalTest {

    public static ThreadLocal<Person> threadLocal = ThreadLocal.withInitial(() -> new Person("yagn", "18", "man", "y"));

    public static void main(String[] args) {
        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====================" + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "=========" + threadLocal.get().getName());
                Person person = threadLocal.get();
                person.setName("duan");
                //打印本地变量
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                person.setName("duan");
                person.setName("aaa");
                person.setName("bbb");
                person.setName("ccc");
                person.setName("ddd");
                person.setName("eee");
                person.setName("fff");
                person.setName("ggg");
            }
        });

        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====================" + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "=========" + threadLocal.get().getName());
                Person person = threadLocal.get();
                person.setName("li");
                //打印本地变量
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
                System.out.println(Thread.currentThread().getName() + "=========after update : " + threadLocal.get().getName());
            }
        });

        t1.start();
        t2.start();

    }
}
