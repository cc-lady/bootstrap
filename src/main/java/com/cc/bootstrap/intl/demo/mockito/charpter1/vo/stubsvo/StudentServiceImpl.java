package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

import java.sql.SQLException;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description StudentService的实现类，委托给studentDAO真正实现内部必要持久化逻辑
 * @createTime 2022年01月06日 14:30:00
 */
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public CreateStudentResponse create(String name, String studentOfclass) {
        CreateStudentResponse response = null;
        try{
            String roleNum= studentDAO.create(name,
                    studentOfclass);
            response = new CreateStudentResponse(null, new Student(roleNum, name));
        }catch(SQLException e) {
            //如果数据访问层出现任何问题，DAO会抛出SQLExc错误。实现 类会捕获异常，并将错误消息设置为响应对象
            response = new CreateStudentResponse
                    ("SQLException"+e.getMessage(), null);
        }catch(Exception e) {
            response = new CreateStudentResponse(e.getMessage(),
                    null);
        }
            return response;
        }
    }
