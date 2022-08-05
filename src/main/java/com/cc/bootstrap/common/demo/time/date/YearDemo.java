package com.cc.bootstrap.common.demo.time.date;

import java.time.Year;

/**
 * @Description: 年
 * Year 类 代表一年。
 * @author: ChenChen
 * @date: 2022/8/4 17:30
 */
public class YearDemo {
    public static void main(String[] args) {
        //下面的示例使用 Year.isLeap方法来确定给定年份是否为闰年。该调用返回true，确认 2012 年是闰年。
        boolean validLeapYear = Year.of(2012).isLeap();
        System.out.println(validLeapYear);
    }
}
