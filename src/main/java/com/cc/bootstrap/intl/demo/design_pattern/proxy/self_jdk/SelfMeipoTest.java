package com.cc.bootstrap.intl.demo.design_pattern.proxy.self_jdk;

import com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk.Customer;
import com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk.Person;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2025-10-13 15:14
 */
public class SelfMeipoTest {

    public static void main(String[] args) throws Exception {
        // 代理对象
        Person person = (Person) new SelfMeipo().getInstance(new Customer());
        System.out.println(person.getClass());
        // 代理对象帮忙开始相亲
        person.findLove();
    }
}
