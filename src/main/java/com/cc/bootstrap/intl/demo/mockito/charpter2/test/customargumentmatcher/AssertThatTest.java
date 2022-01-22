package com.cc.bootstrap.intl.demo.mockito.charpter2.test.customargumentmatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;// 匹配器在这个类里面
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Description AssertThatTest单元测试类 - 使用自定义参数匹配器
 * @author ChenChen
 * @date 2022/1/12
 *
 * @return
 */
@RunWith(MockitoJUnitRunner.class)
public class AssertThatTest {

    /*
     *  使用自定义参数匹配器
     *  一、 比较匹配器 Comparison matchers – equalTo, is, and not
     *
     */
    @Test
    public void verify_Matcher() throws Exception {
        int age = 30;
        assertThat(age, equalTo(30));
        assertThat(age, is(30));
        assertThat(age, not(equalTo(33)));
        assertThat(age, is(not(33)));
    }

    /*
     * 二、复合值匹配器 Compound value matchers – either, both, anyOf, allOf, and not
     *                                        任何一个，两个，任何一个，所有的，而不是
     */
    @Test
    public void verify_multiple_values() throws Exception {
        double marks = 100.00;
        assertThat(marks, either(is(100.00)).or(is(90.9)));
        assertThat(marks, both(not(99.99)).and(not(60.00)));
        assertThat(marks, anyOf(is(100.00),is(1.00),is(55.00),is(88.00),
                is(67.8)));
        assertThat(marks, not(anyOf(is(0.00),is(200.00))));
        assertThat(marks, not(allOf(is(1.00),is(100.00), is(30.00))));
    }
}
