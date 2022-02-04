package com.cc.bootstrap.intl.demo.mockito.charpter3.test.doCallRealMethodanddoNothing;

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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 实际方法 doCallRealMethod 和 什么都不做 doNothing
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
     * 当您想要调用模拟对象上的方法的实际实现时，将使用.doCallRealMethod()方法 。其语法如下：
     * doCallRealMethod().when(mock).someVoidMethod();
     */
    @Test
    public void learn_doCallRealMethod() throws Exception {
        // stubs
        doCallRealMethod().when(loginController).process(request, response);
        when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(Boolean.FALSE);
        when(request.getRequestDispatcher("error.jsp")).thenReturn(dispatcher);

        // test
        controller.doGet(request, response);

        // verify
        verify(request).getRequestDispatcher(eq("error.jsp"));
        verify(dispatcher).forward(request, response);
    }

    /**
     * 什麽都不做
     *
     * 当loginController.process(request, response);什么也不做时，此controller.doGet(request, response);
     * 方法正常结束，不会调用request.getRequestDispatcher(isA(String.class));
     */
    @Test
    public void learn_doNothing() throws Exception {
        // stubs
        when(request.getServletPath()).thenReturn("/logon.do");
        doNothing().when(loginController).process(request, response);

        // test
        controller.doGet(request, response);

        // verify
        verify(request, times(0)).getRequestDispatcher(isA(String.class));
    }
}
