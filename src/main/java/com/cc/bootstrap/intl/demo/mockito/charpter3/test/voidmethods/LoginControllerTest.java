package com.cc.bootstrap.intl.demo.mockito.charpter3.test.voidmethods;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManager;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description void methods - 单元测试 - 使用verify验证方法和参数
 * 返回类型以验证代码的行为。但是，当一个方法不返回一个值，而只是更改了被测试对 象的内部状态时，就很难决定要断言什么。
 * 传统的单元测试需要使用直接输入和输出， 但void方法需要使用间接输出。本类给出测试void方法的测试示例。
 *
 * 在这里，我们将创建一个模拟的Http服务请求、Http服务响应和一个LDAP管理器，并验证是否采取了这些操作。
 * 我们将把LDAPManager的isValidUser方法返回到单元测试成功的用户登录，并返回false到单元测试无效的登录场景。
 * @createTime 2022年01月22日 15:31:00
 */
public class LoginControllerTest {
    private LoginController controller;
    private @Mock HttpServletRequest request;
    private @Mock HttpServletResponse response;
    private @Mock LDAPManager ldapManager;

    /*
     * 成功登录后，进程()方法创建一个用户会话，将用户信息放到会话中，然后发送请 求。
     * 因此，为此，我们需要创建一个模拟HttpSessin对象和请求调度对象：
     */
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher dispatcher;

    @Before
    public void beforeEveryTest(){
        MockitoAnnotations.initMocks(this);//实例化模拟对象
        controller = new LoginController(ldapManager);
    }

    @Test
    public void when_valid_user_credentials_for_login_Then_routes_to_home_page() throws Exception{

        // stubs
        when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(true);
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);//可以将共同需要的代码提到@Before方法中，清理代码
        when(request.getParameter(anyString())).thenReturn("user","pwd");
        // test
        controller.process(request, response);

        // verify
        verify(ldapManager).isValidUser(anyString(),anyString());//验证调用了方法
        verify(request).getSession(true);//验证调用了方法
        verify(session).setAttribute(anyString(), anyString());//验证调用了方法
        verify(request).getRequestDispatcher(eq("home.jsp"));//验证调用了方法，且方法参数是"home.jsp"
        verify(dispatcher).forward(request, response);//验证调用了方法
    }

    @Test
    public void when_invalid_user_credentials_Then_routes_to_login_page() throws Exception{
        // stubs
        when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(false);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter(anyString())).thenReturn("user","pwd");
        // test
        controller.process(request, response);
        // verify
        verify(request).getRequestDispatcher(eq("login.jsp"));//验证调用了方法，且方法参数是"login.jsp"
        verify(dispatcher).forward(request, response);//验证调用了方法
        verify(request).setAttribute("error", "Invalid user name or password");
    }

}
