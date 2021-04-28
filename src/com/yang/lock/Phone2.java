package com.yang.lock;/**
 * @title: Phone
 * @projectName java8test
 * @description: TODO
 * @author yangjianlei
 * @date 2021/3/23 18:19
 */

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Phone
 * @Description: TODO
 * @Author yjl
 * @Date 2021/3/23 
 * @Version V1.0
 */
public class Phone2 {

    public static synchronized void sendMail() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("******sendMail******");
    }
    public static synchronized void sendSMS() throws Exception {
        System.out.println("******sendSMS*******");
    }

    public static void main(String[] args) throws InterruptedException {
        /*Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendMail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(8000);
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();*/
    }
}
