package com.cc.bootstrap.intl.demo.mockito.charpter3.test.voidmethodsthrowexception;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodsthrowexception.DemoController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description void方法抛出异常 - 单元测试
 * 如果LDAPManager抛出异常，Web应用程序将失败。数字控制器服务器是网关；它应该处理任何不需要的异常，并向用户显示正确的错误消息。
 * 我们必须找到一种机制来处理异常。--- 找到机制，否则代码有问题，此示例就测试出应在DemoController的doGet方法中增加异常处理机制。
 * @createTime 2022年01月22日 15:58:00
 */
public class DemoControllerTest {
    DemoController controller;
    @Mock
    LoginController loginController;

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

    @Test
    public void when_subsystem_throws_exception_Then_routes_to_error_page_() throws Exception {
        // stubs
        doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
        when(request.getServletPath()).thenReturn("/logon.do");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        // test
        controller.doGet(request, response);

        // verify
        verify(request).getRequestDispatcher(eq("error.jsp"));
        verify(dispatcher).forward(request, response);
    }
}
