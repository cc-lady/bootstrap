package com.cc.bootstrap.intl.demo.mockito.charpter1.test.mockobjects;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.mockobjectsvo.StudentService;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.mockobjectsvo.StudentServiceMockObject;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description  a test to verify the method invocation 模拟对象测试
 * Mockito框架为模拟对象提供了一个API。它使用代理对象来验证调用和存根调用。
 * @createTime 2022年01月06日 15:54:00
 */
public class StudentServiceTest {
    StudentService service = new StudentService();
    StudentServiceMockObject mockObject = new StudentServiceMockObject();

    @Test
    public void enrolls_students() throws Exception {
        //create 2 students
        Student bob = new Student("001", "Robert Anthony");
        Student roy = new Student("002", "Roy Noon");
        //set mock/spy
        service.setMock(mockObject);
        //invoke method twice
        service.enrollToCourse("english", bob);
        service.enrollToCourse("history", roy);
        //assert that the method was invoked twice - 方法调用了两次
        assertEquals(2, mockObject.invocation("enrollToCourse"));
        //verify wrong information, that enrollToCourse was
        //invoked once, but actually it is invoked twice - 验证错误信息，验证1次实际调用两次
        mockObject.verify("enrollToCourse", 1);
    }
}
