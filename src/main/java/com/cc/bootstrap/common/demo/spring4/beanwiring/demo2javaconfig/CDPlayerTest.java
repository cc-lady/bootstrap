package com.cc.bootstrap.common.demo.spring4.beanwiring.demo2javaconfig;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Description: 验证自动装配
 * 在测试代码中使用System.out.println()是稍微有点棘手的事情。因此，该样例中使用了StandardOutputStreamLog，
 * 这是来源于System Rules库（http://stefanbirkner.github.io/system-rules/index.html）的一个JUnit规则，
 * 该规则能够基于控制台的输出编写断言。在这里，我们断言SgtPeppers.play()方法的输出被发送到了控制台上。
 * @author: ChenChen
 * @date: 2024-02-29 11:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CDPlayerConfig.class)
public class CDPlayerTest {
    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();
    @Autowired
    private CDPlayer player;
    @Autowired
    private CompactDisc cd;
    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

    @Test
    public void play() {
    player.play();
    assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band" +
                " by The Beatles\r\n",log.getLog());
    }
}
