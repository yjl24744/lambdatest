package com.yang.async;/**
 * @title: CompletableFutureTest
 * @projectName java8test
 * @description: TODO
 * @author yangjianlei
 * @date 2021/4/19 11:18
 */

import com.google.common.collect.ImmutableList;
import com.yang.pojo.Person;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @ClassName CompletableFutureTest
 * @Description: java8新异步方式测试
 * @Author yjl
 * @Date 2021/4/19 
 * @Version V1.0
 */
public class CompletableFutureTest {
    public static final ThreadPoolExecutor EXECUTORS = new ThreadPoolExecutor(
            0,
            1,
            20,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20),
            new TheadFactoryName()
    );

    /**
     * CompletableFuture的静态工厂方法:
     * runAsync(Runnable runnable)：使用ForkJoinPool.commonPool()作为它的线程池执行异步代码。
     * runAsync(Runnable runnable, Executor executor)：使用指定的thread pool执行异步代码。
     * supplyAsync(Supplier<U> supplier)：使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值
     * supplyAsync(Supplier<U> supplier, Executor executor)：使用指定的thread pool执行异步代码，异步操作有返回值
     * complete(T t)	完成异步执行，并返回future的结果
     * completeExceptionally(Throwable ex)	异步执行不正常的结束
     */

    /**
     * runAsync 和 supplyAsync 方法的区别是runAsync返回的CompletableFuture是没有返回值的
     */
    public void testJdk8RunAsync() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello");
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }

    /**
     * 而supplyAsync返回的CompletableFuture是由返回值的
     */
    public void testSupplyAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture.supplyAsync(() -> "Hello2").thenAccept(System.out::println);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }

    /**
     * future调用complete(T t)会立即执行。但是complete(T t)只能调用一次，后续的重复调用会失效
     */
    public void testComplete() {
        CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * future调用complete(T t)会立即执行。但是complete(T t)只能调用一次，后续的重复调用会失效
     */
    public void testComplete2() {
        CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");
        future.thenAccept(System.out::println);

        future.complete("World");

    }

    public void testCountDownLatch() {
        List<Person> personList = ImmutableList.of(
                new Person("yang", "18", "男", "y"),
                new Person("li", "19", "男", "l"),
                new Person("duan", "14", "nv", "d"),
                new Person("gao", "15", "nv", "g")
        );

        List<CompletableFuture<Void>> futureList= personList.stream()
                .map(person -> CompletableFuture.runAsync(() -> System.out.println(person)))
                .collect(Collectors.toList());

        CompletableFuture<Void> completableFuture=CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        completableFuture.join();
        completableFuture.thenRun(() -> System.out.println("你好"));
        completableFuture.thenRunAsync(() -> System.out.println("世界"), EXECUTORS);
        // completableFuture.thenRun(() -> System.out.println("你好"));
        // CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).thenAcceptAsync(aVoid -> System.out.println("nihao"), EXECUTORS);
        // CompletableFuture.runAsync(() -> System.out.println("word"), EXECUTORS);

    }

    /**
     * 测试汇聚关系
     */
    public void testAnd() {
        // 任务一：订购航班
        CompletableFuture<String> orderAirplane = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询航班");
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购航班");
            return "航班信息";
        });
        // 任务二：订购酒店
        CompletableFuture<String> orderHotel = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询酒店");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购酒店");
            return "酒店信息";
        });
        // 任务三：任务1与任务2都完成后，才能去订购租车服务
        CompletableFuture<String> hireCar = orderHotel.thenCombine(orderAirplane, (airplane, hotel) -> {
            System.out.println("根据航班家酒店订购租车服务");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "租车信息";
        });
        // 等待任务3执行结构
        System.out.println(hireCar.join());
    }




    public void testJdk5Future() {
        ExecutorService executor = Executors.newCachedThreadPool();

        //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
        Future<String> future = executor.submit(() -> {
            System.out.println("running task");
            Thread.sleep(10000);
            return "return task";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情
        System.out.println("do something else");

        try {
            //等待 future 的执行结果，执行完毕之后打印出来
            System.out.println(future.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {

        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        CompletableFutureTest ct = new CompletableFutureTest();
        // ct.testSupplyAsync();
        // ct.testComplete();
        // ct.testComplete2();
        ct.testCountDownLatch();
        // ct.testAnd();
    }

}
