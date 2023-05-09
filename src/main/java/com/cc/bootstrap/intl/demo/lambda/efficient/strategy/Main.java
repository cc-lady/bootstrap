package com.cc.bootstrap.intl.demo.lambda.efficient.strategy;

/**
 * @Description: 未用lambda表达式和使用lambda表达式的使用对比
 * @author: ChenChen
 * @date: 2023/2/6 10:18
 */
public class Main {
    public static void main(String[] args) {
        //使用策略设计模式，未用lambda表达式---很多类和僵化代码
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        //使用lambda表达式
        //ValidationStrategy是一个函数接口，直接传递lambda表达式
        Validator numericValidator1 =
                new Validator((String s) -> s.matches("[a-z]+"));
        boolean b11 = numericValidator1.validate("aaaa");
        Validator lowerCaseValidator1 =
                new Validator((String s) -> s.matches("\\d+"));
        boolean b21 = lowerCaseValidator1.validate("bbbb");

        //Lambda表达式避免了采用策略设计模式时僵化的模板代码。如果你仔细分
        //析一下个中缘由，可能会发现，Lambda表达式实际已经对部分代码（或策略）进行了封装，而
        //这就是创建策略设计模式的初衷。因此，我们强烈建议对类似的问题，你应该尽量使用Lambda
        //表达式来解决。
    }
}
