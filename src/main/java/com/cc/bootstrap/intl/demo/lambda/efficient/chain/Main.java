package com.cc.bootstrap.intl.demo.lambda.efficient.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Description: 使用场景
 * @author: ChenChen
 * @date: 2023/2/6 14:11
 */
public class Main {
    public static void main(String[] args) {
        //未使用Lambda表达式
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        //使用Lambda表达式
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result2);
    }
}
