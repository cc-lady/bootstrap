package com.cc.bootstrap.intl.demo.mockito.charpter2.test.answeringmethodcalls;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.AjaxController;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.Country;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.CountryDao;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.RetrieveCountryRequest;
import com.cc.bootstrap.page.api.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName AjaxControllerTest
 * @Description AjaxController单元测试类 - 应答方法调用
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {
    private static Logger LOGGER = LoggerFactory.getLogger(AjaxControllerTest.class);

    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    List<Country> countries;
    @Before
    public void setUp() {
        ajaxController = new AjaxController(countryDao);
        countries = new ArrayList<Country>();
        countries.add(new Country("Argentina", "AR", "32"));
        countries.add(new Country("USA", "US", "01"));
        countries.add(new Country("Brazil", "BR", "05"));
        countries.add(new Country("India", "IN", "91"));
    }

    // 应答类
    class SortAnswer implements Answer<Object> {
        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            RetrieveCountryRequest request = (RetrieveCountryRequest)
                    invocation.getArguments()[0];
            final int order = request.getSortOrder().equals("asc")
                    ? 1: -1;
            final String col = request.getSortname();
            Collections.sort(countries, new Comparator<Country>() {
                public int compare(Country arg0, Country arg1) {
                    if ("iso".equals(col))
                        return order * arg0.getIso().compareTo(arg1.getIso());
                    return order * arg0.getName().compareTo(arg1.getName());
                }
            });
            return countries;
        }
    }

    /**
     * @Description 插入方法返回硬编码值，但不能返回动态结果。Mockito框架提供了实时计算结果的 回调。
     *
     * Mockito允许使用通用的答案接口进行存根；这是一个回调。
     * 当调用模拟对象上的存 根方法时，将调用应答对象的答案
     * answer(InvocationOnMock invocation)（调用OnMock调用）方法。
     * 这个 Answer 对象的 answer()方法返回实际的对象。其语法类似于 thenReturn()()和 thenThrow():
     * when(mock.someMethod())。thenAnswer(new Answer() {…});
     * 或者，我们也可以使用以下语法：
     * when(mock.someMethod()).then(answer);
     *
     * 应答接口的定义如下：
     * public interface Answer<T> {
     *  T answer(InvocationOnMock invocation) throws Throwable;
     * }
     * InvocationOnMock参数是回调的重要组成部分。它还提供了传递给方法和模拟对象的参数。
     * 以下方法使用了InvocationOnMock来获取参数和模拟对象：
     * Object[] args = invocation.getArguments();
     * Object mock = invocation.getMock();
     *
     * @author ChenChen
     * @date 2022/1/19
     * @return
     */
    @Test
    public void sorting_asc_on_iso() {
        when(request.getParameter(anyString())).thenReturn("1", "10",
                "asc", "iso");
        when(countryDao.retrieve(isA(RetrieveCountryRequest.class)))
                .thenAnswer(new SortAnswer());
        Page<Country> response = ajaxController.retrieve(request);
        LOGGER.info("response countryList [{}]", response.getRecords());

        assertEquals("01", response.getRecords().get(0).getIso());
        assertEquals("05", response.getRecords().get(1).getIso());
        assertEquals("32", response.getRecords().get(2).getIso());
        assertEquals("91", response.getRecords().get(3).getIso());
    }
}
