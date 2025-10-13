package com.cc.bootstrap.intl.demo.design_pattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: cglib方式实现动态代理
 * @author: ChenChen
 * @date: 2025-10-13 15:41
 */
public class CglibMeipo implements MethodInterceptor {

    // 获取被代理对象的代理对象，通过Proxy.newProxyInstance（JDK方式）
    public Object getInstance(Class<?> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        // 设置为即将生成新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 代理对象功能增强
        before();
        // 被代理对象实现核心功能
        Object obj = methodProxy.invokeSuper(o, objects);
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
