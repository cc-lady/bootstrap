package com.cc.bootstrap.intl.demo.mockito.charpter3.test.determiningmockdetails;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.determiningmockdetails.Dependency1;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.determiningmockdetails.Dependency2;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.determiningmockdetails.ServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mockingDetails;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 确定对象是模拟对象还是间谍对象
 * mockingDetails(object).isMock /  mockingDetails(object).isSpy()
 *
 * 有时，我们需要确定一个物体是模拟的还是间谍的。当对象使用@ippeltMocks注释 时可能出现这种情况；
 *  对象可以注入间谍或模拟对象。
 * 我们可以使用 Mockito.mockingDetails找到该类型。
 * 它可以识别一个特定的物体是模拟模型还是间谍。
 * @createTime 2022年02月04日 14:11:00
 */
@RunWith(MockitoJUnitRunner.class)
public class MockDetailsTest {
    @Mock
    Dependency1 dep1;
    @Spy
    Dependency1 dep;
    @Mock
    Dependency2 dep2;
    @InjectMocks
    ServiceImpl service;
    @Test
    public void when_determining_type() throws Exception {
        assertNotNull(service);
        assertTrue(mockingDetails(service.getDependency2()).isMock());
        assertTrue(mockingDetails(dep).isSpy());

        assertTrue(mockingDetails(service.getDependency1()).isMock());//与顺序无关
    }
}
