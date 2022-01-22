package com.cc.bootstrap.intl.demo.mockito.charpter2.test.argumentmatchers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.AjaxController;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.Country;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.CountryDao;
import com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo.RetrieveCountryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName AjaxControllerTest
 * @Description AjaxController单元测试类 - 参数匹配器
 * 为了对这个类进行单元测试，我们需要创建一个HttpServlet请求对象，用可测试的数据填充它，
 * 然后隔离国家Dao/数据库访问调用。
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {

    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    /**
     * @Description 参数匹配器未匹配的返回默认值，因为默认返回null，所以本方法测试预计返回空指针异常
     * @author ChenChen
     * @date 2022/1/12
     *
     * @return
     */
    /* 参数匹配器：
     * 模拟对象返回预期值，但当它们需要为不同的 参数返回不同的值时，参数匹配器就会发挥作用。
     * 当一个方法被存根时，Mockito返回期望值。如果该方法接受参数，则该参数在 执行期间必须匹配。
     * when(mockObject.getValue(1)).thenReturn(expected value);
     * mockito提供内置参数匹配器：such as anyInt(), anyDouble(), anyString(), anyList(), and anyCollection().
     * 更多内置参数匹配器和其他 hamcrest matchers 可参考：
     * https://github.com/mockito/mockito/blob/master/src/org/mockito/Matchers.java
     * 其他匹配器的例子是isA(java.lang。Class<T> clazz), any(java.lang. 类<T>clazz)和eq(T)或eq（原始值）。
     * isA匹配器检查传递的对象是否是在isA参数中传递的类类型的实例。任何(T)匹配 器也以同样的方式工作。
     */
    @Test(expected = NullPointerException.class)
    public void argument_matcher() {
        // 仅当匹配"page"时返回硬编码的值，其余的将会是返回默认值
        // 如果返回类型为int或短或长，则对于整数和长等包装类 型，它返回0。如果它为布尔值返回NULL，如果对象为null，它将返回false，以此类推。
        when(request.getParameter("page")).thenReturn("1","10", "name", "name");
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

    /* 一、通配符匹配器
     * mockito提供内置参数匹配器：such as anyInt(), anyDouble(), anyString(), anyList(), and anyCollection().
     *
     * 测试对测试代码调用方法。当被调用的方法创建一个新对象并将其传递给模拟对象时， 测试方法并没有对该新对象的引用。
     * 因此，测试不能用特定的值存根模拟方法，因为该 值对测试方法不可用。在这种情况下，我们使用通配符匹配器。
     *
     * 二、在使用参数匹配器时，所有参数都必须由匹配器提供。
     *
     */
    @Test
    public void argument_matcher_all() {
        // 所有匹配anyString()将会是返回默认值
        when(request.getParameter(anyString())).thenReturn("1","10", "name", "name");
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country());

        // 如果使用参数匹配器，则所有参数都要用参数匹配器，否则报错，例如以下会报错：
        /*
         * This exception may occur if matchers are combined with raw values:
                //incorrect:
                someMethod(anyObject(), "raw String");
            When using matchers, all arguments have to be provided by matchers.
            For example:
                //correct:
                someMethod(anyObject(), eq("String by matcher"));
         */
//        when(countryDao.retrieve2(isA(RetrieveCountryRequest.class), true)).thenReturn(countryList);
        // 修改后不报错
        when(countryDao.retrieve2(isA(RetrieveCountryRequest.class), isA(Boolean.class))).thenReturn(countryList);
        Page<Country> response = ajaxController.retrieve2(request, true);
        assertEquals(1, response.getCurrent());
        assertEquals(1, response.getTotal());
        assertEquals(1, response.getRecords().size());
    }
}
