package com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @Description: 测试JDK实现动态代理
 * @author: ChenChen
 * @date: 2025-10-11 14:30
 */
public class JDKMeipoTest {
    public static void main(String[] args) throws Exception {
        // 代理对象
        Person person = (Person) new JDKMeipo().getInstance(new Customer());
        // 代理对象帮忙开始相亲
        person.findLove();

        // 以下代码获取代理类的.class文件，通过反编译工具可以查看源码
        byte[] bytes = ProxyGenerator.generateProxyClass("&Proxy0", new Class[]{Person.class});
        FileOutputStream fos = new FileOutputStream("&Proxy0.class");
        fos.write(bytes);
        fos.close();
    }
}
