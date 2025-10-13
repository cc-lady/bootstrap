package com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: JDK方式实现动态代理
 *
 * 静态代理：父亲给儿子找对象，代理对象是父亲，被代理对象是儿子，特定的父亲和儿子
 * 动态代理：
 * 不仅包括父亲给儿子找对象，如果找对象这项业务发展成了一个产业，出现了媒婆、婚介所等，那么用静态代理成本太高了，
 * 需要一个更加通用的解决方案，满足任何单身人士找对象的需求。
 * @author: ChenChen
 * @date: 2025-10-11 14:16
 */
public class JDKMeipo implements InvocationHandler {
    // 被代理对象，把引用保存下来
    private Object target;

    // 获取被代理对象的代理对象，通过Proxy.newProxyInstance（JDK方式）
    public Object getInstance(Object target) throws Exception {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理对象功能增强
        before();
        // 被代理对象实现核心功能
        Object obj = method.invoke(this.target, args);
        // 代理对象功能增强
        after();
        return obj;
    }

    private void before() {
        System.out.println("我是媒婆，我要给你找对象，现在开始确认你的需求");
        System.out.println("开始物色");
    }

    private void after() {
        System.out.println("如果合适的话，就开始办事");
    }
}
