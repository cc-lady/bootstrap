package com.cc.bootstrap.demo;

//import org.junit.runner.RunWith;
//import org.junit.runners.BlockJUnit4ClassRunner;

import com.cc.bootstrap.common.demo.test.StringTest;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName JunitTestString.java
 * @Description Junit4编写单元测试
 * @createTime 2021年08月14日 23:18:00
 */

//@RunWith(BlockJUnit4ClassRunner.class)//默认运行器，可以省略
public class JunitTestString {
    private static Logger LOGGER = LoggerFactory.getLogger(JunitTestString.class);

    private static final String TEST_ERROR_MESSAGE = "the param is not valid!";

    @BeforeClass
    public static void beforeClassMethod() {
        LOGGER.info("beforClassMethod : environment init...");
    }

    @Before
    public void beforeMethodMethod() {
        LOGGER.info("beforeMethodMethod : method init...");
    }

    /**
     * @title
     * @description 测试用例1：被截取字符串长度大于1
     * @author ChenChen
     * @param
     * @updateTime 2021/8/15 0:15
     * @throws
     */
    @Test
    public void WithLengthMoreThanOne(){//testStr
        String paramStr = "aaa,aaa,";
        String result = null;
        try {
            result = StringTest.subStrOffLast(paramStr);
        } catch (Exception e) {
            LOGGER.error("error: {}", e.getMessage(), e);
        }
        Assert.assertEquals("测试用例1：被截取字符串长度大于1，结果不符", "aaa,aaa", result);
    }

    /**
     * @title
     * @description 测试用例2：被截取字符串为null，预期结果：抛出异常RuntimeException，异常信息为the param is not valid!
     * @author ChenChen
     * @param
     * @updateTime 2021/8/15 0:15
     * @throws
     */
    @Test(expected = RuntimeException.class)
    public void testNullStr(){
        String paramStr = null;
        try {
            StringTest.subStrOffLast(paramStr);
        } catch (Exception e) {
            LOGGER.error("error: {}", e.getMessage(), e);
            Assert.assertEquals(JunitTestString.TEST_ERROR_MESSAGE, e.getMessage());
            throw e;
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();//在4.13中已过时

    /**
     * @title
     * @description 测试用例3：被截取字符串为空串""，预期结果：抛出异常RuntimeException，异常信息为the param is not valid!
     * @author ChenChen
     * @param
     * @updateTime 2021/8/15 0:15
     * @throws
     */
    @Test
    public void testWithStrLengthZero(){
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(JunitTestString.TEST_ERROR_MESSAGE);

        String paramStr = "";
        try {
            StringTest.subStrOffLast(paramStr);
        } catch (Exception e) {
            LOGGER.error("error: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * @title
     * @description 测试用例4：与测试用例3相同功能，使用Assert.assertThrows()写法
     * 被截取字符串为空串""，预期结果：抛出异常RuntimeException，异常信息为the param is not valid!
     * @author ChenChen
     * @param
     * @updateTime 2021/8/15 0:15
     * @throws
     */
    @Test
    public void testWithStrLengthZero2(){
        RuntimeException exception = Assert.assertThrows(RuntimeException.class,
                () -> StringTest.subStrOffLast(""));
        Assert.assertEquals(JunitTestString.TEST_ERROR_MESSAGE, exception.getMessage());
    }

    @After
    public void afterMethodMethod() {
        LOGGER.info("afterMethodMethod : method clear...");
    }

    @AfterClass
    public static void afterClassMethod() {
        LOGGER.info("afterClassMethod : environment clear...");
    }

}
