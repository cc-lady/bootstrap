package com.cc.bootstrap.common.demo.calculate;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @Description 根据表达式计算算术运算结果 spring-expression
 *
 * @author ChenChen
 * @return
 * @date 2022/11/11 10:35
 */
public class SpringTest {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("100*(2+400)*1+66");
        int result = (Integer) expression.getValue();
        System.out.println("计算结果是：" + result);
    }
}
