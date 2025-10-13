package com.cc.bootstrap.intl.demo.design_pattern.proxy.self_jdk;

import java.lang.reflect.Method;

/**
 * @Description: 自己手写的SelfInvocationHandler
 * @author: ChenChen
 * @date: 2025-10-13 15:11
 */
public class SelfMeipo implements SelfInvocationHandler{
    // 被代理对象，把引用保存下来
    private Object target;

    // 获取被代理对象的代理对象，通过Proxy.newProxyInstance（JDK方式）
    public Object getInstance(Object target) throws Exception {
        this.target = target;
        Class<?> clazz = target.getClass();
        return SelfProxy.newProxyInstance(new SelfClassLoader(), clazz.getInterfaces(), this);
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
