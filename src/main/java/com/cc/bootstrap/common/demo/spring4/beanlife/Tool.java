package com.cc.bootstrap.common.demo.spring4.beanlife;

/**
 * @Description: 工具，注入SpringBeanLifePoJo
 * @author: ChenChen
 * @date: 2024-02-26 15:47
 */
public class Tool {
    private String name;
    private String type;

    public Tool() {
        System.out.println("Tool实例化-无参构造");
    }

    public Tool(String name, String type) {
        this.name = name;
        this.type = type;
        System.out.println("Tool实例化-有参构造");
    }
}
