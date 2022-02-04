package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LDAPManagerImpl;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods.LoginController;

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
 * @Description 修改后的DemoController - 调用错误处理程序和消息存储库接口以获得有 意义的消息，并将该消息传递给用户。
 * @createTime 2022年01月22日 15:54:00
 */
@WebServlet("/DemoController")
public class DemoController extends HttpServlet {
    private final LoginController loginController;
    private final ErrorHandler errorHandler;
    private final MessageRepository messageRepository;
    //我们可以重构控制器构造函 数，以传递登录控制器类的模拟时刻。
    public DemoController(LoginController loginController, ErrorHandler errorHandler,
                          MessageRepository messageRepository) {
        this.loginController = loginController;
        this.errorHandler = errorHandler;
        this.messageRepository = messageRepository;
    }

    // doPost方法直接转移调用doGet方法，都用doGet方法执行
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // doGet执行具体请求
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String urlContext = req.getServletPath();
            if (urlContext.equals("/")) {
                req.getRequestDispatcher("login.jsp").forward(req, res);
            } else if (urlContext.equals("/logon.do")) {
                loginController.process(req, res);
            } else {
                req.setAttribute("error", "Invalid request path '" +
                        urlContext + "'");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        } catch (Exception ex) {
            String errorMsg = ex.getMessage();
            Error errorDto = new Error();
            errorDto.setTrace(ex.getStackTrace());
            errorHandler.mapTo(errorDto);
            /*
             * 此部分代码可能调用不到，但是需要测试未调用的行
             * 可是使用doAnswer方法：doAnswer(answer).when(mock).someVoidMethod();
             */
            if(errorDto.getErrorCode() != null){
                errorMsg = messageRepository.lookUp (errorDto.
                        getErrorCode());
            }
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
