package com.cc.bootstrap.common.demo.spring4.beanlife;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: BeanConfig
 * @author: ChenChen
 * @date: 2024-02-26 16:56
 */
@Configuration
public class BeanConfig {
    @Bean
    public SpringBeanLifePoJo springBeanLifePoJo() {
        return new SpringBeanLifePoJo(tool());
    }

    @Bean
    public Tool tool() {
        return new Tool("cc", "修电器");
    }
}
