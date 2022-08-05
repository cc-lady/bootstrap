package com.cc.bootstrap.common.demo.time.date;

import java.time.Month;
import java.time.YearMonth;

/**
 * @Description: 月年
 * YearMonth 类表示特定年份的月份。
 * @author: ChenChen
 * @date: 2022/8/4 17:26
 */
public class YearMonthDemo {
    public static void main(String[] args) {
        //下面的示例使用YearMonth.lengthOfMonth()方法来确定若干年和月组合的天数。
        YearMonth date = YearMonth.now();
        System.out.printf("%s: %d%n", date, date.lengthOfMonth());

        YearMonth date2 = YearMonth.of(2010, Month.FEBRUARY);
        System.out.printf("%s: %d%n", date2, date2.lengthOfMonth());//2010-02: 28

        YearMonth date3 = YearMonth.of(2012, Month.FEBRUARY);
        System.out.printf("%s: %d%n", date3, date3.lengthOfMonth());//2012-02: 29


    }
}
