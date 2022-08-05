package com.cc.bootstrap.common.demo.time;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @Description: Month 月
 * @author: ChenChen
 * @date: 2022/8/4 17:18
 */
public class MonthDemo {
    public static void main(String[] args) {
        //以下代码行使用maxLength方法打印二月份的最大可能天数。输出为“29”：
        System.out.printf("%d%n", Month.FEBRUARY.maxLength());

        Month month = Month.AUGUST;
        Locale locale = Locale.getDefault();
        System.out.println(month.getDisplayName(TextStyle.FULL, locale));//八月
        System.out.println(month.getDisplayName(TextStyle.NARROW, locale));//8
        System.out.println(month.getDisplayName(TextStyle.SHORT, locale));//八月
    }
}
