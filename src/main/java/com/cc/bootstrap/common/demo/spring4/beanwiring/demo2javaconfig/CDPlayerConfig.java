package com.cc.bootstrap.common.demo.spring4.beanwiring.demo2javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 使用java代码显示装配bean（去掉了@ComponentScan注解，使用javaconfig方式装配bean）
 * @author: ChenChen
 * @date: 2024-02-28 15:59
 */
@Configuration
public class CDPlayerConfig {

    /*
     * @Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文中的bean。方法体中包含了最终产生bean实例的逻辑。
     * 默认情况下，bean的ID与带有@Bean注解的方法名是一样的。在本例中，bean的名字将会是sgtPeppers。
     */
    @Bean//等同于@Bean(name="sgtPeppers")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    /**
     * @Description 借助JavaConfig实现注入
     * 在JavaConfig中装配bean的最简单方式就是引用创建bean的方法。
     * cdPlayer()的方法体与sgtPeppers()稍微有些区别。在这里并没有使用默认的构造器构建实例，
     * 而是调用了需要传入CompactDisc对象的构造器来创建CDPlayer实例。
     * 看起来，CompactDisc是通过调用sgtPeppers()得到的，但情况并非完全如此。
     * 因为sgtPeppers()方法上添加了@Bean注解，Spring将会拦截所有对它的调用，并确保直接返回该方法所创建的bean，而不是每次都对其进行实际的调用。
     * @author ChenChen
     * @return com.cc.bootstrap.common.demo.spring4.beanwiring.demo2javaconfig.CDPlayer
     * @date 2024-02-29 10:53
     */
    @Bean
    public CDPlayer cdPlayer() {
        // 引用创建bean的方法sgtPeppers()，实现装配
        return new CDPlayer(sgtPeppers());
    }
}
