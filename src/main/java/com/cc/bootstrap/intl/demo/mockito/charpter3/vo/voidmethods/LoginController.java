package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description LoginController - 执行logon.do的请求
 * @createTime 2022年01月22日 15:23:00
 */
public class LoginController {
    private final LDAPManager ldapManager;//验证用户名密码
    public LoginController(LDAPManager ldapMngr) {
        this.ldapManager = ldapMngr;
    }

    // controller执行logon.do的请求
    public void process(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String encrypterPassword = req.getParameter
                ("encrypterPassword");
        if (ldapManager.isValidUser(userName, encrypterPassword)) {
            req.getSession(true).setAttribute("user", userName);
            req.getRequestDispatcher("home.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "Invalid user name or password");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}
