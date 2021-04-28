package com.yang.lock;/**
 * @title: Phone
 * @projectName java8test
 * @description: TODO
 * @author yangjianlei
 * @date 2021/3/23 18:19
 */

import com.yang.pojo.Person;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Phone
 * @Description: TODO
 * @Author yjl
 * @Date 2021/3/23 
 * @Version V1.0
 */
public class Phone3 {

    public final Object A = new Object();
    public final Object B = new Object();

    public void sendMail() throws Exception {
        System.out.println("=====A");
        synchronized (A) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("******sendMail******");
        }
    }
    public void sendSMS() throws Exception {
        System.out.println("=====B");
        synchronized (A) {
            System.out.println("******sendSMS*******");
        }
    }

    public void add(Person person) throws Exception {
        synchronized (B) {
            for (int j = 0; j < 10; j++) {
                person.setAge(j+"");
                System.out.println(Thread.currentThread().getName()+"****"+person.getAge());
            }
        }
    }
}
