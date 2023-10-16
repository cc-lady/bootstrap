package com.cc.bootstrap.demo.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式测试
 * @author: ChenChen
 * @date: 2023-10-12 15:37
 */
@Slf4j
public class Demo5_RegexTest {

    @Test
    public void testRegex() {
        String testStr = "I paid $30 for 100 apples,\n" +
                "50 oranges, and 60 pears.\n" +
                "I saved $5 on this order.";
        String regexp = "\\b(?<!\\$)\\d+\\b";
        Matcher matcher = Pattern.compile(regexp).matcher(testStr);

//        // 替换  反向引用的正则表达式必须是子表达式
//        String result = matcher.replaceAll("$1\\U$2\\E$3");
//        log.info("matcher.replaceAll.result() = {}", result);

        // 能返回多个匹配结果：正则表达式加了()变为子表达式，这里就匹配不上了，去掉才能匹配上
        while (matcher.find()) {
            log.info("matcher.group() = {}", matcher.group());
        }
        log.info("matcher.matches() = {}", matcher.matches());//false  因为不是全部匹配，只是testStr的一部分
//        10:56:01.280 [main] INFO  Demo5_RegexTest -matcher.group() = 100
//        10:56:01.280 [main] INFO  Demo5_RegexTest -matcher.group() = 50
//        10:56:01.280 [main] INFO  Demo5_RegexTest -matcher.group() = 60
//        10:56:01.280 [main] INFO  Demo5_RegexTest -matcher.matches() = false
    }
}
