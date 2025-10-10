package com.cc.bootstrap.intl.demo.design_pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 容器式单例：将单例存储在一个容器中
 * @author: ChenChen
 * @date: 2025-10-10 15:45
 */
public class ContainerSingleton {
    private ContainerSingleton() {}

    private static Map<String,Object> ioc = new ConcurrentHashMap<>();//存储在容器中
    // 提供全局唯一访问点
    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}
