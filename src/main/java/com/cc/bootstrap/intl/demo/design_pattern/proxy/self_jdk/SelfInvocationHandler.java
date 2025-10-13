package com.cc.bootstrap.intl.demo.design_pattern.proxy.self_jdk;

import java.lang.reflect.Method;

/**
 * @Description: 自己手写的调用处理器接口
 * @author: ChenChen
 * @date: 2025-10-11 14:53
 */
public interface SelfInvocationHandler {
    // 调用核心程序
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
