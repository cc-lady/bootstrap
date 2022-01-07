package com.cc.bootstrap.intl.demo.mockito.charpter1.test.dummy;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 创建一个假的学生课程，并扩展学生课程。这是一个虚拟的对象。
 * 虚拟对象将不是真正实现的对象，并且提供零功能或值。
 * 假学生类从所有方法中抛出一个运行时异常。以下是假学生的主体：
 * @createTime 2022年01月06日 14:15:00
 */
public class DummyStudent extends Student {
    public DummyStudent() {
        super(null, null);
    }
    public String getRoleNumber() {
        throw new RuntimeException("Dummy student");
    }
    public String getName() {
        throw new RuntimeException("Dummy student");
    }
}
