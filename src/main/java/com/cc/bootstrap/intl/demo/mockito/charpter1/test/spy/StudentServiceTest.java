package com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.spyvo.StudentService;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 编写一个测试来检查方法调用和参数。
 * 间谍秘密地获取对手或非常重要的人的信息。顾名思义，一个间谍物体在监视一个真实的物体。间谍是存根的一个变体，
 * 但间谍不是只设置期望，而是记录对合作者的方法调用。间谍可以作为被测单元的间接输出，也可以作为审计日志。
 *
 * 间谍对象检查行为。
 * @createTime 2022年01月06日 15:32:00
 */
public class StudentServiceTest {
    StudentService service = new StudentService();
    StudentServiceSpy spy = new StudentServiceSpy();

    @Test
    public void enrolls_students() throws Exception {
        //create student objects
        Student bob = new Student("001", "Robert Anthony");
        Student roy = new Student("002", "Roy Noon");
        //set spy
        service.setSpy(spy);
        //enroll Bob and Roy
        service.enrollToCourse("english", bob);
        service.enrollToCourse("history", roy);
        //assert that the method was invoked twice - 测试调用了两次
        assertEquals(2, spy.invocation("enrollToCourse"));
        //get the method arguments for the first call - 获取第一次调用的参数
        List<Object> methodArguments = spy.arguments
                ("enrollToCourse", 1).getParams();
        //get the method arguments for the 2nd call - 获取第二次调用的参数
        List<Object> methodArguments2 = spy.arguments
                ("enrollToCourse", 2).getParams();
        // 确认Bob是否先注册了英语
        //verify that Bob was enrolled to English first - 验证第一次调用时的参数情况
        assertEquals("english", methodArguments.get(0));//第一个参数是english
        assertEquals(bob, methodArguments.get(1));//第二个参数是Bob\
        // 核实罗伊是否有历史记录
        //verify that Roy was enrolled to history - 验证第二次调用时的参数情况
        assertEquals("history", methodArguments2.get(0));//第一个参数是history
        assertEquals(roy, methodArguments2.get(1));//第二个参数是roy
    }
}
