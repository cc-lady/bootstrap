package com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Description: 为了测试组件扫描的功能，我们创建一个简单的JUnit测试，它会创建Spring上下文，并判断CompactDisc是不是真的创建出来了。
 * 因为没有写在test目录，依赖spring-boot-starter-test的scope要改为compile（原test）
 * @author: ChenChen
 * @date: 2024-02-28 16:04
 */
@RunWith(SpringJUnit4ClassRunner.class)//以便在测试开始的时候自动创建Spring的应用上下文。
@ContextConfiguration(classes=CDPlayerConfig.class)//注解@ContextConfiguration会告诉它需要在CDPlayerConfig中加载配置。因为CDPlayerConfig类中包含了@ComponentScan，因此最终的应用上下文中应该包含CompactDiscbean。
public class CompactDiscTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Autowired
    private CompactDisc cd;
    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);

        CompactDisc cd1 = (CompactDisc) applicationContext.getBean("sgtPeppers");
        assertNotNull(cd1);

        // 表明注入的就是applicationContext容器中的bean
        assertEquals(cd1, cd);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
