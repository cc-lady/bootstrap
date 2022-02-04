package com.cc.bootstrap.intl.demo.mockito.charpter3.test.voidmethodscallbacks;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.ErrorHandler;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodsthrowexception.DemoController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description void方法抛出异常 - 单元测试
 * 我们将创建一个匿名的回答对象，访问错误对象，并设置错误代码字符串。
 * @createTime 2022年01月22日 15:58:00
 */
public class DemoControllerTest {
    DemoController controller;
    @Mock
    LoginController loginController;
    @Mock
    ErrorHandler errorHandler;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;

    @Before
    public void beforeEveryTest(){
        MockitoAnnotations.initMocks(this);
        controller = new DemoController(loginController);
    }

    /**
     * 外部代码依赖项可以以void方法处理数据，例如，它可以发送电子邮件或更新数据库 行。我们可以通过模拟依赖关系很容易地存void方法，
     * 但有时void方法可能会改变输 入参数对象的属性，例如，它可以设置传入的错误对象的错误代码作为参数，我们可 以在计算中使用修改后的值。
     * 在这个场景中，如果我们存根了void方法，那么它并不能帮助我们修改或添加存根方法的参数属性。
     * 因此，我们的测试可能会失败，或者代 码的一部分可能仍未被测试。
     *
     * 使用doAnswer方法：doAnswer(answer).when(mock).someVoidMethod();
     * 我们将创建一个匿名的回答对象，访问错误对象，并设置错误代码字符串。
     */
    @Test
    public void when_subsystem_throws_any_exception_Then_finds_error_message_and_routes_to_error_page_()
            throws Exception {
        // stubs
        doThrow(new IllegalStateException("LDAP error")).when
                (loginController).process(request, response);
        doAnswer(new Answer<Object>() {
                     @Override
                     public Object answer(InvocationOnMock invocation) throws
                             Throwable {
                         // 修改参数数据
                         Error err = (Error) invocation.getArguments()[0];
                         err.setErrorCode("123");
                         return err;
                     }
                 }
        ).when(errorHandler).mapTo(isA(Error.class));
        when(request.getServletPath()).thenReturn("/logon.do");
        when(request.getRequestDispatcher(anyString()))
                .thenReturn(dispatcher);
        // test
        controller.doGet(request, response);

        // verify
        verify(request).getRequestDispatcher(eq("error.jsp"));
        verify(dispatcher).forward(request, response);
    }
}
