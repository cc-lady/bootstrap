package com.cc.bootstrap.intl.demo.mockito.charpter3.test.doReturn;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManager;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.DemoController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.ErrorHandler;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学习doReturn
 * @createTime 2022年01月22日 15:58:00
 */
public class DemoControllerTest {

    DemoController controller;
    @Mock
    LoginController loginController;
    @Mock
    ErrorHandler errorHandler;
    @Mock
    MessageRepository messageRepository;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    LDAPManager ldapManager;
    @Mock
    RequestDispatcher dispatcher;

    @Before
    public void beforeEveryTest(){
        MockitoAnnotations.initMocks(this);
        controller = new DemoController(loginController, errorHandler, messageRepository);
    }

    /**
     * doReturn
     * doRetuern()方法与tenReturn()类似，但仅在不能使用when(mock).thenReturn（）时使用。
     * when().thenReturn()方法比doReturn()更容易读。此外，doReturn()的类型也不安全。
     * thenReturn方法会检查方法返回类型，如果传递了不安全的类型，则 会引发编译错误。
     * 您可以在使用spy间谍对象时使用doReturn()。下面是使用doReturn() 测试的语法：
     * doReturn(value).when(mock).method(argument);
     *
     * 下面展示了doReturn使用的缺点：它是不可读的和容易出错的。
     */
    @Test
    public void when_do_return_is_not_safe() throws Exception {
        when(request.getServletPath()).thenReturn("/logon.do");
        assertEquals("/logon.do", request.getServletPath());

        /**
         * doReturn这样用不安全
         * org.mockito.exceptions.misusing.UnfinishedStubbingException:
         * Unfinished stubbing detected here:
         * -> at charpter3.test.doReturn.DemoControllerTest.when_do_return_is_not_safe(DemoControllerTest.java:69)
         */
//        doReturn(1.111d).when(request.getServletPath());
        request.getServletPath();
    }
}
