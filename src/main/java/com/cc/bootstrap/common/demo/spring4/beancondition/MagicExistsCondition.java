package com.cc.bootstrap.common.demo.spring4.beancondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: 条件化地创建Bean：当存在magic属性时，创建
 * @author: ChenChen
 * @date: 2024-03-01 15:07
 */
public class MagicExistsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.containsProperty("magic");
    }
}
