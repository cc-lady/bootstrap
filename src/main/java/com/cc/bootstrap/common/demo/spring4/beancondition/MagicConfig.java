package com.cc.bootstrap.common.demo.spring4.beancondition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: MagicConfig
 * 在Spring 4之前，很难实现这种级别的条件化配置，但是Spring 4引入了一个新的@Conditional注解，它可以用到带有@Bean注解的方法上。
 * 如果给定的条件计算结果为true，就会创建这个bean，否则的话，这个bean会被忽略。
 * @author: ChenChen
 * @date: 2024-03-01 15:04
 */
@Configuration
public class MagicConfig {

    /**
     * 如果满足这个条件的话，matches()方法就会返回true。所带来的结果就是条件能够得到满足，
     * 所有@Conditional注解上引用MagicExistsCondition的bean都会被创建。
     * 如果这个属性不存在的话，就无法满足条件，matches()方法会返回false，这些bean都不会被创建。
     */
    @Bean
    @Conditional(MagicExistsCondition.class)//条件化地创建Bean：条件类内涵逻辑
    public MagicBean magicBean() {
        return new MagicBean();
    }
}
