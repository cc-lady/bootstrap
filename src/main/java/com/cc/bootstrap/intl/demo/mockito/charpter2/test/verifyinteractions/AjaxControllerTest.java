package com.cc.bootstrap.intl.demo.mockito.charpter2.test.verifyinteractions;

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
 * @Description AjaxController单元测试类 - 验证零交互和不再有交互
 * @createTime 2022年01月07日 09:38:00
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {

    private @Mock HttpServletRequest request;
    private @Mock CountryDao countryDao;
    private @InjectMocks AjaxController ajaxController;

    /**
     * @Description The verifyZeroInteractions (object, mocks) method verifies that no
     * interactions happened on the given mocks.验证在给定的模拟对象上没有发生交互。
     *
     * @author ChenChen
     * @date 2022/1/19
     *
     * @return 
     */
    @Test 
    public void verify_zero_interaction() {
        // 由于没有对模拟对象调用任何方法， 因此测试将通过
        // 如果您的代码依赖于两个或两个以上的协作者，这一点很有用。对于给定的输入， 只有一个协作者应该处理该请求，而其他协作者应该忽略该请求。
        verifyZeroInteractions(request,countryDao);
    }


    /**
     * @Description 检查任何给定的模拟是否有任何未验证的交互。
     * 我们可以在验证一个模拟方法之后使用此方法，以确保在模拟上没有调用任何其他方 法
     * 这通常不是一个好的实践，因为它使你的测试过于脆弱，最终测试的不仅仅是你关 心的。
     * @author ChenChen
     * @date 2022/1/19
     *
     * @return 
     */
    @Test public void verify_nomore_interaction() {
        request.getParameter("page");
//        request.getContextPath();  // 注释后此单元测试通过
        verify(request).getParameter(anyString());
        //this will fail getContextPath() is not verified
        verifyNoMoreInteractions(request);
    }
}
