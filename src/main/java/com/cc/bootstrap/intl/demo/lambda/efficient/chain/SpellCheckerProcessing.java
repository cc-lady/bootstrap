package com.cc.bootstrap.intl.demo.lambda.efficient.chain;

/**
 * @Description: 文本处理 - 替换字符
 * @author: ChenChen
 * @date: 2023/2/6 14:10
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    public String handleWork(String text){
        return text.replaceAll("labda", "lambda");
    }
}
