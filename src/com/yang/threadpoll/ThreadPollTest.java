package com.yang.threadpoll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPollTest
 * @Description: TODO
 * @Author yjl
 * @Date 2021/4/27
 * @Version V1.0
 */
public class ThreadPollTest {
    public static final ThreadPoolExecutor EXECUTORS = new ThreadPoolExecutor(0, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread() + "--------");
            };
            EXECUTORS.execute(runnable);
        }
    }
}
