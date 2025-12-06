package com.cc.bootstrap.intl.demo.design_pattern.delegate;

/**
 * @Description: 员工B
 * @author: ChenChen
 * @date: 2025-10-13 16:26
 */
public class EmployeeB implements IEmployee{

    @Override
    public void doing(String command) {
        System.out.println("我是员工B，开始干" + command + "工作");
    }
}
