package com.cc.bootstrap.intl.demo.lambda.efficient.strategy;

/**
 * @Description: IsNumeric 实现策略二
 * @author: ChenChen
 * @date: 2023/2/6 10:17
 */
public class IsNumeric implements ValidationStrategy {
    public boolean execute(String s){
        return s.matches("\\d+");
    }
}
