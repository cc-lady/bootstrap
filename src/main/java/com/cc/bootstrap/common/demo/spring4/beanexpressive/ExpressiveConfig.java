package com.cc.bootstrap.common.demo.spring4.beanexpressive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Description: 使用外部的属性来装配BlankDisc bean
 * @author: ChenChen
 * @date: 2024-03-01 16:24
 */
@Configuration
@PropertySource("classpath:com/cc/bootstrap/common/demo/spring4/beanexpressive/app.properties")
public class ExpressiveConfig {
    @Autowired
    private Environment environment;

    @Bean
    public BlankDisc blankDisc() {
        return new BlankDisc(environment.getProperty("disc.title"), environment.getProperty("disc.artist"));
    }
}
