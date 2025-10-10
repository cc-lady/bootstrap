package com.cc.bootstrap.intl.demo.design_pattern.singleton.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @Description: 测试单例模式
 * @author: ChenChen
 * @date: 2025-10-10 14:29
 */
public class SingletonTest {

    public static void main(String[] args) {
        // 测试饿汉式单例
        testReflect(SeriableSingleton.class);
        testSeriable(SeriableSingleton.class);

        // 测试懒汉式单例
        testReflect(LazyInnnerSingleton.class);
        testSeriable(LazyInnnerSingleton.class);
    }

    private static void testSeriable(Class<?> clazz) {
        System.out.println("开始测试类：" + clazz.getName() + "，单例模式是否不受序列化影响，结果：");
        FileOutputStream os = null;
        try {
            Object o1 = null;
            // 对照实例
            Object o2 = null;
            if (clazz == SeriableSingleton.class) {
                o2 = SeriableSingleton.getInstance();
            } else if (clazz == LazyInnnerSingleton.class) {
                o2 = LazyInnnerSingleton.getInstance();
            }

            os = new FileOutputStream("object2.obj");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(o2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("object2.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            o1 = ois.readObject();
            ois.close();

            System.out.println(o1);
            System.out.println(o2);
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testReflect(Class clazz) {
        System.out.println("开始测试类：" + clazz.getName() + "，单例模式是否不受反射影响，结果：");
        try {
            // 通过反射获取私有构造方法
            Constructor constructor = clazz.getDeclaredConstructor(null);
            // 强制访问
            constructor.setAccessible(true);

            // 暴力初始化
            Object o1 = null;
            if (clazz == SeriableSingleton.class) {
                o1 = constructor.newInstance();
            } else if (clazz == LazyInnnerSingleton.class) {
                o1 = LazyInnnerSingleton.getInstance();
            }
            // new 两次
            Object o2 = null;
            try {
                o2 = constructor.newInstance();
            } catch (Exception e) {
                o2 = o1;
            }
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
