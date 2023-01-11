package com.cc.bootstrap.common.demo.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式匹配代码
 * @author: ChenChen
 * @date: 2023/1/11 15:59
 */
public class MatchUtil {
    public static void main(String[] args) {
        String name = "sysproj";
        String str = "sysproj_011";
        String pattern = ""+name+"_[0-9]{2,}";

        //以下两种方式一样，str.matches(pattern)的源码就是这样
        //第一种
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());//true
        //第二种
        System.out.println(str.matches(pattern));//true
    }
}
