package com.cc.bootstrap.intl.demo.lambda.efficient.strategy;

/**
 * @Description: 策略模式接口
 * @author: ChenChen
 * @date: 2023/2/6 10:16
 */
public interface ValidationStrategy {
    boolean execute(String s);
}
