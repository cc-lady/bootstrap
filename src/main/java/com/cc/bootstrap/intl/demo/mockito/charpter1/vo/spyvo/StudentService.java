package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.spyvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy.MethodInvocation;
import com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy.StudentServiceSpy;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学生的课程注册服务
 *
 * @createTime 2022年01月06日 15:16:00
 */
public class StudentService {

    //修改学生服务类以设置一个间谍，并记录对间谍对象的每个方法调用
    private StudentServiceSpy spy;
    public void setSpy(StudentServiceSpy spy) {
        this.spy = spy;
    }

    private Map<String, List<Student>> studentCouseMap = new HashMap<>();

    // 该课程注册的学生不包含该学生，则注册此学生进入该课程。
    public void enrollToCourse(String courseName,Student student){
        // 间谍记录对间谍对象的每个方法调用
        MethodInvocation invocation = new MethodInvocation();
        invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
        spy.registerCall(invocation);

        // 方法实际逻辑
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
