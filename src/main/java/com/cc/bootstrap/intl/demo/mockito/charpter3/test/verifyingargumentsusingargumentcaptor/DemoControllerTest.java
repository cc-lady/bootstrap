package com.cc.bootstrap.intl.demo.mockito.charpter3.test.verifyingargumentsusingargumentcaptor;

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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 使用 参数捕获器 捕获参数
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
     * 一、学习使用参数捕获器验证参数
     * Mockito通过使用等()方法验证自然Java样式中的参值。这也是匹配参数的推荐方法，因为它使测试干净和简单。
     * 但在某些情况下，在实际验证之后对某些参数 进行断言是有帮助的。
     * 箭捕获对象的定义如下：
     * ArgumentCaptor<T> argCaptor= ArgumentCaptor.forClass(T.class); 其中T是参数的类型，例如字符串或用户定义的类。
     * 以下语法用于捕获参数：
     * verify(mockObject).methodA(argCaptor.capture());
     * 如果一个参数捕获器对象捕获了多个调用的参数，则可以通过调用getAllValues()方法来检索捕获的值。
     * 获取值()方法返回List<T>，getValue()方法返回T，这是最后一个方法调用结果。
     * 在这里，T是参数类的类型，例如一个整数或任何Java类类型。下面的代码使用一个符号符号捕获器来验证传递到查找方法中的参数。
     */
    @Test
    public void learn_using_argument_captor() throws Exception {
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

        // 验证参数是“123”
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(messageRepository).lookUp(captor.capture());//验证调用，捕获调用时的参数
        assertEquals("123", captor.getValue());//验证调用时的参数是"123"
    }

    @Mock
    Service service;
    /**
     * 二、下面的示例演示了如何捕获集合参数。
     * 强制转换。
     */
    @Test
    public void when_captures_collections() throws Exception {
        // 使用强制转换，这将给您提供关于不安全强制转换的警告；您可以通过使 用@抑制警告（“未检查”）注释构造来抑制警告：
        Class<List<String>> listClass = (Class<List<String>>) (Class)List.class;
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(listClass);
        // test
        service.call(Arrays.asList("a","b"));

        // verify
        verify(service).call(captor.capture());
        assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
    }

    /**
     * 三、如何捕获可变参数和数组
     */
    @Test
    public void when_capturing_variable_args() throws Exception {
        String[] errorCodes = {"a","b","c"};
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass
                (String.class);
        // test
        messageRepository.lookUp(errorCodes);

        // verify
        verify(messageRepository).lookUp(captor.capture(),captor.capture()
                ,captor.capture());
        assertTrue(captor.getAllValues().containsAll(Arrays.asList
                (errorCodes)));
    }
}
