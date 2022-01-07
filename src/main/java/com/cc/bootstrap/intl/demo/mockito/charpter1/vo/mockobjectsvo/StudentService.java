package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.mockobjectsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy.MethodInvocation;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学生注册课程服务 - 用于测试模拟对象
 * @createTime 2022年01月06日 15:47:00
 */
public class StudentService {
    // 设置间谍对象
    private StudentServiceMockObject mock;
    public void setMock(StudentServiceMockObject mock) {
        this.mock = mock;
    }

    private Map<String, List<Student>> studentCouseMap = new HashMap<>();

    // 学生注册课程
    public void enrollToCourse(String courseName,Student student){
        // 间谍对象记录方法调用
        MethodInvocation invocation = new MethodInvocation();
        invocation.addParam(courseName).addParam(student)
                .setMethod("enrollToCourse");
        mock.registerCall(invocation);

        // 实际方法逻辑
        List<Student> list = studentCouseMap.get(courseName);
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!list.contains(student)) {
            list.add(student);
        }
        studentCouseMap.put(courseName, list);
    }
}
