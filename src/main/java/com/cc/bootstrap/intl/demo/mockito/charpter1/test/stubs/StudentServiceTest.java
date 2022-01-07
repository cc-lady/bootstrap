package com.cc.bootstrap.intl.demo.mockito.charpter1.test.stubs;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo.CreateStudentResponse;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo.StudentService;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 测试SQLExcoty条件。创建一个测试类，并将带有短根的DAO传递给服务实现
 * --------存根可以非常方便地模拟错误条件和外部依赖关系（您可以通过模拟实现相同的事情 ；这只是一种方法）。
 *  假设您需要测试一个查找JNDI资源并要求该资源返回一些值的 代码。您不能从JUnit测试中查找JNDI资源；
 *  您可以存根JNDI查找代码并返回一个具 有硬编码值的硬根对象
 * @createTime 2022年01月06日 14:38:00
 */
public class StudentServiceTest {
    private StudentService studentService;
    @Test
    public void when_connection_times_out_then_the_student_is_not_saved() {
        studentService = new StudentServiceImpl(new ConnectionTimedOutStudentDAOStub());
        String classNine = "IX";
        String johnSmith = "john Smith";
        CreateStudentResponse resp = studentService.create(johnSmith, classNine);
        assertFalse(resp.isSuccess());
    }
}
