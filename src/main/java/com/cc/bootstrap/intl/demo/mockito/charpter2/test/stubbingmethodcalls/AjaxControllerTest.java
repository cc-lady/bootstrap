package com.cc.bootstrap.intl.demo.mockito.charpter2.test.stubbingmethodcalls;

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
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName AjaxControllerTest
 * @Description AjaxController单元测试类 - 存根的使用方法
 * 为了对这个类进行单元测试，我们需要创建一个HttpServlet请求对象，用可测试的数据填充它，
 * 然后隔离国家Dao/数据库访问调用。
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {
    // 1.2.时候这样写，使用@Mock注释时用注释情况
//    HttpServletRequest request;
//    CountryDao countryDao;
    // 3.4.使用@Mock注释时这样写
    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    @Before
    public void setUp(){
        /*
         * 我们将使用Mockito框架来创建一个模拟的Httpservlet请求对象，并通过终止数据 库调用来隔离国家Dao访问调用。
         * 可以通过静态方法mock()创建模拟对象。您需要调用Mockito.mock()方法或静态导入Mockito的模拟()方法。
         */
        // 1. import org.mockito.Mockito;
//        request = Mockito.mock(HttpServletRequest.class);
//        countryDao = Mockito.mock(CountryDao.class);
        // 2. import static org.mockito.Mockito.mock;
//        request = mock(HttpServletRequest.class);
//        countryDao = mock(CountryDao.class);
        // 3. 使用@Mock注释，MockitoAnnotations.initMocks(this);
//        MockitoAnnotations.initMocks(this);
        // 4. 使用@Mock注释，可以不加上MockitoAnnotations.initMocks(this);，
        // 使用@RunWith(MockitoJUnitRunner.class)
    }

    @Test
    public void retrieves_empty_country_list() throws Exception {
        List<Country> list = new ArrayList<Country>();
        list.add(new Country());
        // mockito支持存根 - when().thenReturn();
        /*
         * when()方法表示触发器-何时存根它。以下方法用于表示触发器操作或触发触发器时 的操作。
         * thenReturn（要返回的值）：此方法返回一个给定的值。
         * Throw（可抛出）：此方法抛出给定的异常。
         * thenAnswer（Answer answer）：在这种方法中，与返回硬编码值不同，执行一个动态的、用户定义的逻辑；
         *              更像是假测试加倍。答案是一个接口。实现应答接口需要动态代码逻辑。
         *  In this method, unlike returning a hardcoded
            value, a dynamic, user-defined logic is executed; more like fake test doubles.
            Answer is an interface. Dynamic code logic is needed to implement the
            Answer interface.
         * thenCallRealMethod()：这个方法调用模拟对象/间谍上的真实方法。
         *
         * thenReturn方法有一个变体；它可以返回一个硬编码值，也可以接受硬编码值 的变量参数。以下是接下来的三个变体：
         *  • thenReturn(value) 然后返回（值、值...）
         *  • thenReturn(value).thenReturn(value2).thenReturn(value3)
         *   然后，Return(value)变体为每个方法调用返回相同的硬编码值，
         * 而 when(mock.someMethod())。thenReturn（10,5,100）返回以下值：
         *  在第一次调用期间，mock.someMethod()返回10
         *  在第二次调用期间，mock.someMethod()返回5
         *  在第三次调用期间，mock.someMethod()返回100
         *  在所有其他调用中，mock.someMethod()返回100
         */
        when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenReturn(list);
        assertTrue(countryDao.retrieve(new RetrieveCountryRequest()).size() == 1);
    }

    @Test
    public void retrieves_empty_country_list1() throws Exception {
        // anyString匹配器被用作通用参数匹配器。这意味 着，无论将什么值传递给getparater方法，它都将返回一个硬编码的值。
        when(request.getParameter(anyString())).thenReturn("1","10", "name", "name");
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country());
        // 使用isA匹配器存存道的检索方法，得到以下内容：
        // 如果使用检索国家请求对象调用检索方法，它将返回国家列表
        when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenReturn(countryList);
        Page<Country> response = ajaxController.retrieve(request);
        assertEquals(1, response.getCurrent());
        assertEquals(1, response.getTotal());
        assertEquals(1, response.getRecords().size());
    }
}
