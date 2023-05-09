package com.cc.bootstrap.intl.demo.lambda.efficient.chain;

/**
 * @Description: 文本处理：开头
 * @author: ChenChen
 * @date: 2023/2/6 14:10
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    public String handleWork(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
}
