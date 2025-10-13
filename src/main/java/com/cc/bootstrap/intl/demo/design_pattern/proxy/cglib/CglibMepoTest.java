package com.cc.bootstrap.intl.demo.design_pattern.proxy.cglib;

import com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk.Customer;
import com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk.Person;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2025-10-13 15:45
 */
public class CglibMepoTest {
    public static void main(String[] args) throws Exception {
        // 代理对象
        Person person = (Person) new CglibMeipo().getInstance(Customer.class);
        // 代理对象帮忙开始相亲
        person.findLove();
    }
}
