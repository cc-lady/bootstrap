package com.cc.bootstrap.intl.demo.lambda.efficient.strategy;

/**
 * @Description: Validator 使用
 * @author: ChenChen
 * @date: 2023/2/6 10:17
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
        this.strategy = v;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
