package com.cc.bootstrap.intl.demo.mockito.charpter2.test.verifyingmethodcalls;

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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName AjaxControllerTest
 * @Description AjaxController单元测试类 - 验证方法调用
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {

    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    /* 验证方法调用
     * 为了验证冗余的方法调用，或者如果没有调用存短的方法，但从测试的角度来看很重要，
     * 我们应该手动验证调用。我们需要使用静态verify方法。
     *
     * 模拟对象用于存根外部依赖关系。我们设置一个期望值，然后用一个模拟对象返 回一个期望值。
     * 在某些情况下，不应该调用模拟对象的行为/方法，或者有时我们 可能需要调用该方法N（一个数字）次。验证方法可验证对模拟对象的调用。
     *  Mockito不会自动验证所有存根调用；JMock会自动执行此操作。
     *
     * 如果不应该调用存根行为，而是由于代码中的错误而被调用，那么验证方法将标 记该错误（但我们必须手动验证它）。
     * void方法不返回值；verv对于测试void方 法的行为非常方便（稍后解释）。
     * verify()方法有一个重载版本，它将验证模式（最少、大多数、次数等）作为参数。
     * (AtLeast, AtMost, Times, and so on) as an argument.
     * Times模式是一个包，org.mockito.internal.verification的Mockito框架类，它采 用了整数参数，, wantedNumberOfInvocations.
     *
     * 如果将0传递给Times，则推断在测试路径中不会调用该方法。我们可以将0传递给 Times（0），
     * 以确保不调用卖方法或买方法。如果一个负数被传递给时代构造器， Mockito抛出时代异常-org.mockito.exceptions.base。并显示负值，
     * 这里不允许出现负值的错误。验证方法与验证以下方法：
     *
     */
    @Test
    public void argument_matcher() {
        when(request.getParameter(anyString())).thenReturn("1","10", "name", "name");
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country());

        when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenReturn(countryList);
        // test
        Page<Country> response = ajaxController.retrieve(request);

        // verify
        //  The following methods are used in conjunction with verify:
        // 1. times(int wantedNumberOfInvocations):它被调用N次。如果调用次数与预期不符，则测试失败。
        verify(countryDao, times(1)).retrieve(isA(RetrieveCountryRequest.class));

        // 2. never(): 这从未被调用或被调用为times（0）。
        verify(countryDao, never()).retrieve2(isA(RetrieveCountryRequest.class), isA(Boolean.class));

        // 3. atLeastOnce(): 这至少被调用一次。如果多次调用该方法，它工作良好，但如果没有调用 该方法，则失败。
        verify(countryDao, atLeastOnce()).retrieve(isA(RetrieveCountryRequest.class));

        // 4. atLeast(int minNumberOfInvocations):至少调用N次。
        // 如果调用方法的次数超过最低的次数， 它工作正常，但如果方法没有调用最低的次数，则失败。
        verify(countryDao, atLeast(1)).retrieve(isA(RetrieveCountryRequest.class));

        // 5. atMost(int maxNumberOfInvocations): 最多N次调用。如果调用的方法次数超过指定的时间，它会 失败。
        verify(countryDao, atMost(1)).retrieve(isA(RetrieveCountryRequest.class));

        // 6.only(): 这用于验证在模拟上只调用了一个方法。如果在模拟对象上调用了任何 其他方法，则它将失败。
        verify(countryDao, only()).retrieve(isA(RetrieveCountryRequest.class));

    }
}
