package com.cc.bootstrap.intl.demo.design_pattern.singleton.thread;

/**
 * @Description: 线程单例代码测试
 * @author: ChenChen
 * @date: 2025-10-10 16:10
 */
public class ThreadLocalSingletonTest {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance()));
        t1.start();
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance()));
        t2.start();

        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":"+ ThreadLocalSingleton.getInstance());
    }
}
