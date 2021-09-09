package com.cc.bootstrap.page.user;

import com.cc.bootstrap.common.demo.mockito.MockitoTestService;
import com.cc.bootstrap.page.feign.CustomConfigurationFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName TestUserWithMockito.java
 * @Description 使用Mockito进行单元测试
 * @createTime 2021年08月16日 09:07:00
 */
@SpringBootTest//@SpringBootTest替代了spring-test中的@ContextConfiguration注解，
// 目的是加载ApplicationContext，启动spring容器。
// 使用@SpringBootTest时并没有像@ContextConfiguration一样显示指定locations或classes属性，
// 原因在于@SpringBootTest注解会自动检索程序的配置文件，检索顺序是从当前包开始，逐级向上查找被@SpringBootApplication或
// @SpringBootConfiguration注解的类。
@RunWith(SpringJUnit4ClassRunner.class)//关联SpringBoot Test，使运行JUnit时同时启动Spring
public class TestUserWithMockito {
    private static Logger LOGGER = LoggerFactory.getLogger(TestUserWithMockito.class);

    @InjectMocks//在被测类对象声明的时候加上
    private MockitoTestService mockitoTestService;

    @Mock//不能像@Autowired那样靠spring自动注入依赖类，因为这里APMInfoService内部依赖的类都是Mock的对象，
    // 必须要显式创建类实例Mockito才能注入成功。
    private CustomConfigurationFeign customConfigurationFeign;

    @Before//被调用到的方法写桩
    public void setUp() {
        // 准备桩数据
        String resultTest = "[{\"nameSpace\":\"woody\",\"applicationName\":\"woodyapp\",\"test\":{\"myName\":\"cc\"," +
                "\"myAge\":28},\"podLableName\":\"woodyapp\"},{\"nameSpace\":\"woody2\"," +
                "\"applicationName\":\"woodyapp2\",\"test\":{\"myName\":\"cc\",\"myAge\":28}}]\n";

        // 写桩方法
        try {
            when(customConfigurationFeign.getCustomConfigs()).thenReturn(resultTest);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCustomConfigs() throws JsonProcessingException {
        try {
            boolean result = mockitoTestService.isExistConfig();
            TestCase.assertEquals("测试用例：存在配置测试失败！", Boolean.TRUE, Boolean.valueOf(result));
        }catch (Exception e){
            LOGGER.error("调用失败", e);
            TestCase.fail("测试用例：存在配置测试失败！");
            throw e;
        }
    }
}
