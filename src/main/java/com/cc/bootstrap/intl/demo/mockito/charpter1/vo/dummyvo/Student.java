package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学生
 * @createTime 2022年01月06日 14:07:00
 */
public class Student {
    private final String roleNumber;
    private final String name;
    public Student(String roleNumber, String name) {
        this.roleNumber = roleNumber;
        this.name = name;
    }

    public String getRoleNumber() {
        return roleNumber;
    }

    public String getName() {
        return name;
    }
}
