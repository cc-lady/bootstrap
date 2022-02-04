package com.cc.bootstrap.intl.demo.mockito.charpter3.test.mockitoannotations;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.verifyingargumentsusingargumentcaptor.Service;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManager;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.DemoController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.Error;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.ErrorHandler;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description Mockito注解
 *
 * 到Mockito支持@Mock注释。和@Mock一样，Mockito提供了三个有用的注释， 即@Spy，@Captor和@InjectMocks：
 * •@Captor：这简化了裁判捕获器的创建，当要捕获的参数是一个可怕的通用类时，这很有用
 * •@Spy：这会创建给定对象的间谍；使用它来代替spy(Object)
 * •@InjectMocks：它使用构造器注入、设置器注入或field 注入自动将模拟或间谍field 注入测试对象
 *
 * @createTime 2022年01月27日 09:36:00
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {

    @Captor
    ArgumentCaptor<List<String>> captor;//注释创建了符号符号捕获对象，我们不需要将其类型转换为类<列表<String>>
    @Mock
    Service service;

    @Spy
    ErrorHandler errorHandler;

    @InjectMocks
    DemoController controller;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;

    /*
     * 使用@InjectMocks注解
     */
    @Test
    public void when_mocks_are_injected() throws Exception {
        // subs
        when(request.getServletPath()).thenReturn("/");
        when(request.getRequestDispatcher(anyString())).thenReturn
                (dispatcher);
        // test
        controller.doGet(request, response);
        // verify
        verify(request).getRequestDispatcher(eq("login.jsp"));
    }

//    @Before
//    public void beforeEveryTest(){
//      MockitoAnnotations.initMocks(this);// 类上有了@RunWith(MockitoJUnitRunner.class)，就不用写这个代码了
//      controller = new DemoController(loginController, errorHandler, messageRepository);// 有了@InjectMocks，就不用这个了
//    }

    /*
     * 使用@Captor注解
     */
    @Test
    public void when_captor_annotation_is_used() {
        // test
        service.call(Arrays.asList("a","b"));

        // verify
        verify(service).call(captor.capture());//捕获参数
        assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));//验证参数
    }

    /*
     * 使用@Spy注解
     * 这个和书上写的不一样，可以给interface接口创建spy对象
     */
    @Test
    public void when_spy_annotation_is_used() throws Exception {
        assertNotNull(errorHandler);
    }
}
