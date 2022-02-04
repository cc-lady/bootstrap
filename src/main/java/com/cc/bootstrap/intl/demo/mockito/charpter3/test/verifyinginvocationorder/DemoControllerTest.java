package com.cc.bootstrap.intl.demo.mockito.charpter3.test.verifyinginvocationorder;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.verifyingargumentsusingargumentcaptor.Service;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManager;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.DemoController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.Error;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.ErrorHandler;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 验证调用顺序
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
    Service service;

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
     * 一、学习验证调用顺序
     * Mockito如果使用模拟API以给定的顺序执行，从而便于验证。它允许我们创建一个模 拟的顺序，
     * 并验证所有模拟的所有调用的调用顺序。
     * 使用以下语法使用模拟对象创建顺序：
     * InOrder inOrder=inOrder(mock1,mock2,...mockN);
     * 将使用以下语法检查方法的调用顺序：
     * inOrder.verify(mock1).methodCall1();
     * inOrder.verify(mock2).methodCall2();
     * 如果在mock1的方法Call1()之前调用mock2的方法Call2()，则测试失败。
     * 以下测试将 验证该测试的顺序：
     */
    @Test
    public void when_inorder() throws Exception {
        // test
        request.getServletPath();
        service.call(Arrays.asList("a","b"));

        // verify
        InOrder inOrder=inOrder(request,service);
        // mock2如果先验证，则顺序错误，会报错VerificationInOrderFailure
//        inOrder.verify(service).call(anyList());
        inOrder.verify(request).getServletPath();
        inOrder.verify(service).call(anyList());
    }
}
