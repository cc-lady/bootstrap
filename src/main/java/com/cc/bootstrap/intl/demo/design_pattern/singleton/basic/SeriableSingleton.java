package com.cc.bootstrap.intl.demo.design_pattern.singleton.basic;

import java.io.Serializable;

/**
 * @Description: 饿汉式单例模式：在类加载的时候就立即初始化，创建单例对象
 * 优点：绝对线程安全，效率高（没有加任何锁）
 * 缺点：不管用不用都创建对象，可能会浪费内存
 *
 * 单例模式：确保一个类在任何情况下只有一个实例，并提供一个全局访问点
 * @author: ChenChen
 * @date: 2025-10-10 14:23
 */
public class SeriableSingleton implements Serializable {
    private static final SeriableSingleton INSTANCE = new SeriableSingleton();//单例

    // 防止反射破坏单例
    private static int constructorIndex = 0;
    private SeriableSingleton() {
        constructorIndex++;
        if (constructorIndex > 1) {
            throw new RuntimeException("单例模式仅可实例化一次！");
        }
    }

    // 提供全局唯一访问点
    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    // 防止序列化破坏单例
//    虽然增加readResolve（）方法返回实例解决了单例模式被破坏的问题，但是实际上实例化了两次，
//    只不过新创建的对象没有被返回而已。如果创建对象的动作发生频率加快，就意味着内存分配开销也会随之增大，
//    难道真的就没办法从根本上解决问题吗？下面讲的注册式单例也许能帮助到你。
    private Object readResolve() {
        return INSTANCE;
    }
}
