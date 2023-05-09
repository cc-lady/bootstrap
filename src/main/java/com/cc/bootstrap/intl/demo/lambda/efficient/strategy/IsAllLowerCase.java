package com.cc.bootstrap.intl.demo.lambda.efficient.strategy;

/**
 * @Description: IsAllLowerCase 实现策略一
 * @author: ChenChen
 * @date: 2023/2/6 10:17
 */
public class IsAllLowerCase implements ValidationStrategy {
    public boolean execute(String s){
        return s.matches("[a-z]+");
    }
}
