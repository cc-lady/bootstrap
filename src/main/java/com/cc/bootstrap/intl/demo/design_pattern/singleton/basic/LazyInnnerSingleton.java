package com.cc.bootstrap.intl.demo.design_pattern.singleton.basic;

import java.io.Serializable;

/**
 * @Description: 饿汉式单例模式：被外部调用时才创建单例
 * 使用内部类的方式可以防止加锁导致性能下降的问题
 * @author: ChenChen
 * @date: 2025-10-10 15:04
 */
public class LazyInnnerSingleton implements Serializable {
    private LazyInnnerSingleton() {
        // 防止反射破坏单例
        if (null != LazyHolder.LAZY) {
            throw new RuntimeException("单例模式仅可实例化一次！");
        }
    }

    // 提供全局唯一访问点
    public static final LazyInnnerSingleton getInstance() {
        return LazyHolder.LAZY;
    }

    // 默认不加载，使用时才加载内部类
    private static class LazyHolder {
        private static final LazyInnnerSingleton LAZY = new LazyInnnerSingleton();//单例
    }

    // 防止序列化破坏单例
//    虽然增加readResolve（）方法返回实例解决了单例模式被破坏的问题，但是实际上实例化了两次，
//    只不过新创建的对象没有被返回而已。如果创建对象的动作发生频率加快，就意味着内存分配开销也会随之增大，
//    难道真的就没办法从根本上解决问题吗？下面讲的注册式单例也许能帮助到你。
    private Object readResolve() {
        return LazyHolder.LAZY;
    }
}
