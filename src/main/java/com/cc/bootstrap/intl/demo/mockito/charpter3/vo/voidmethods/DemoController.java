package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description servlet遗留代码 - 为了测试void methods
 * @createTime 2022年01月22日 15:18:00
 */
@WebServlet("/DemoController")
public class DemoController extends HttpServlet {
    private LoginController loginController;
    public DemoController() {
        loginController = new LoginController( new LDAPManagerImpl());
    }

    // doPost方法直接转移调用doGet方法，都用doGet方法执行
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // doGet执行具体请求
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String urlContext = req.getServletPath();//获取url
        //url是/，就重定向到login.jsp页面
        if(urlContext.equals("/")) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        //url是logon.do，执行loginController.process
        }else if(urlContext.equals("/logon.do")) {
            loginController.process(req, res);
        //其余url直接报错
        }else {
            req.setAttribute("error", "Invalid request path'"+urlContext+"'");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
