package com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk;

/**
 * @Description: 相亲客户
 * @author: ChenChen
 * @date: 2025-10-11 14:29
 */
public class Customer implements Person{
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("有6块腹肌");
    }
}
