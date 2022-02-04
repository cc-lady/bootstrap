package com.cc.bootstrap.intl.demo.mockito.charpter3.test.resettingmockobjects;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.mockitosettings.Bar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 重置模拟对象
 * @createTime 2022年02月04日 14:02:00
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetingMockObjectsTest {

    /**
     * reset()方法将清除存根。
     *
     * 不建议重置模拟，因为这表明你的测试可能做得太多了，而且你可能应该再使用新的模拟进行一次测试。
     */
    @Test
    public void when_resetting_mocks() throws Exception {
        Bar bar= Mockito.mock(Bar.class);
        when(bar.getName()).thenReturn("***");
        assertNotNull(bar.getName());
        reset(bar);
        //Bar is reset, the getName() stub is cleared
        assertNull(bar.getName());
    }
}
