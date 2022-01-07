package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description CreateStudentResponse 创建学生响应类
 * @createTime 2022年01月06日 14:26:00
 */
public class CreateStudentResponse {
    private final String errorMessage;
    private final Student student;
    public CreateStudentResponse(String errorMessage, Student student) {
        this.errorMessage = errorMessage;
        this.student = student;
    }
    public boolean isSuccess(){
        return null == errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public Student getStudent() {
        return student;
    }
}
