package com.cc.bootstrap.intl.demo.mockito.charpter2.test.throwingexceptions;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.AjaxController;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.Country;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.CountryDao;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.RetrieveCountryRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName AjaxControllerTest
 * @Description AjaxController单元测试类 - 测试异常
 * 为了对这个类进行单元测试，我们需要创建一个HttpServlet请求对象，用可测试的数据填充它，
 * 然后隔离国家Dao/数据库访问调用。
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {

    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    // JUnit4.0提供了一种使用@Test（预期的=<异常>）来测试异常的方法。
    // 我们将将国家dao访问调用抛出异常，并使用@Test（执行的=）断言异常。如果该测 试没有抛出任何异常，那么它将会失败：
    @Test(expected=RuntimeException.class)
    public void when_system_throws_exception() {
        when(request.getParameter(anyString())).thenReturn("1", "10",
                "name", "name");
        /*
         * 假设抛出异常
         * 若要从void方法中抛出异常，请使用以下代码语法： doThrow(exception).when(mock).voidmethod(arguments);
         * 不建议您检查和抛出运行时间异常。相反，我们可以在生产代码中使用一 个特定的异常。在JUint4中，存在一个用于异常处理的预期异常规则API。
         */
        when(countryDao.retrieve(isA(RetrieveCountryRequest.class)))
                .thenThrow(new RuntimeException("Database failure"));
        Page<Country> response = ajaxController.retrieve(request);
    }
}
