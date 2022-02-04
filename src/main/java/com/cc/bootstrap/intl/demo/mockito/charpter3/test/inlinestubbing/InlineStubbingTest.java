package com.cc.bootstrap.intl.demo.mockito.charpter3.test.inlinestubbing;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.mockitosettings.Bar;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.mockitosettings.Tar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 内联存根 -- 创建模拟对象同时存根他的内部对象
 * @createTime 2022年02月04日 14:06:00
 */
@RunWith(MockitoJUnitRunner.class)
public class InlineStubbingTest {

    /*
     * 莫基托允许我们在修剪它的同时创建模型。
     * 基本上，它允许您在一行代码中创建一个存根。这将有助于保持测试代码的干净性。
     * 例如，在测试中的字段初始化期间，可以创建和存根一些存根：
     */
    Bar bar = when(mock(Bar.class).getTar()).thenReturn(new
            Tar()).getMock();
    @Test
    public void when_stubbing_inline() throws Exception {
        assertNotNull(bar);
        assertNotNull(bar.getTar());
    }
}
