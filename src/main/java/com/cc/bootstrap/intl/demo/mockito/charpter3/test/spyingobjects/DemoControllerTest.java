package com.cc.bootstrap.intl.demo.mockito.charpter3.test.spyingobjects;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.verifyingargumentsusingargumentcaptor.Service;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManager;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.DemoController;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.ErrorHandler;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.MessageRepository;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description spy 间谍-监视对象 （部分模拟）
 * 模拟间谍允许我们使用真实的对象而不是模拟，通过替换一些短短的方法。这种行为 允许我们测试遗留代码。
 * spy对于遗留代码很有用，因为您不能从正在测试的代码中调 用一些测试障碍方法，而且，您也不能模拟需要测试的类。
 * 间谍可以在不模仿被测试代 码的情况下破坏这些测试障碍。间谍可以存不可测试的方法，这样其他方法可以很容易 地测试。
 * 您也可以使用间谍，而不做任何短，只是使用它们来验证两个完全真实的对 象之间的交互。
 * 一旦对间谍对象上的方法设置了期望，间谍对象就不再返回原始值。它开始返回存根值，但仍然显示其他未存根的方法的原始行为。
 * 莫基托可以为一个真实的物体创造一个间谍。与存根不同的是，当我们使用间谍 对象时，会调用实际方法（除非方法被存根）。
 * 间谍也被称为部分模拟。以下是间谍声明：
 * SomeClass realObject = new RealImplemenation();
 * SomeClass spyObject = spy(realObject);
 *
 * @createTime 2022年01月27日 09:21:00
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
     * 一、间谍对象使用例子
     */
    @Test
    public void when_spying_real_objects() throws Exception {
        Error error = new Error();
        error.setErrorCode("Q123");
        Error spyError = spy(error);
        //call real method from spy
        assertEquals("Q123", spyError.getErrorCode());
        //Changing value using spy
        spyError.setErrorCode(null);
        //verify spy has the changed value
        assertEquals(null, spyError.getErrorCode());//未存根时修改，结果修改
        //Stubbing method
        when(spyError.getErrorCode()).thenReturn("E456");
        //Changing value using spy
        spyError.setErrorCode(null);
        //Stubbed method value E456 is returned NOT NULL
        assertNotEquals(null, spyError.getErrorCode());//存根后修改，修改不起作用
        //Stubbed method value E456
        assertEquals("E456", spyError.getErrorCode());
    }

    /**
     * 二、监视真实对象和对间谍对象调用真实方法有副作用；
     * 要免疫这个副作用，使 用doRetern()而不是thenReturn()。
     * 下面的代码描述了监视和调用thenReturn()的副作用：
     *
     * 间谍对象在尝试存根获取（索引）时调用了一个真实的方法，!!!与模拟对象不同的是，真实的方法被调用了!!!，
     * 但它失败了，出现了一个边界错误。
     * 可以使用doRetern()保护此故障.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void when_doReturn_fails() throws Exception {
        List<String> list = new ArrayList<String>();
        List<String> spy = spy(list);
        //impossible the real list.get(0) is called and fails
        //with IndexOutofBoundsException, as the list is empty
        when(spy.get(0)).thenReturn("not reachable");
    }

    /**
     * 使用doRetern()保护故障
     */
    @Test
    public void when_doReturn_success() throws Exception {
        List<String> list = new ArrayList<String>();
        List<String> spy = spy(list);
        //doReturn fixed the issue
        doReturn("now reachable").when(spy).get(0);
        assertEquals("now reachable", spy.get(0));
    }
}
