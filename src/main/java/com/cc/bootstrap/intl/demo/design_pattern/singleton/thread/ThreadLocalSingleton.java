package com.cc.bootstrap.intl.demo.design_pattern.singleton.thread;

/**
 * @Description: 线程单例：不是全局单例，但是每个线程中是实例是唯一的。利用ThreadLocal
 * @author: ChenChen
 * @date: 2025-10-10 16:05
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> INSTANCE = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton(){}

    // 提供全局唯一访问点
    public static ThreadLocalSingleton getInstance(){
        return INSTANCE.get();
    }
}
