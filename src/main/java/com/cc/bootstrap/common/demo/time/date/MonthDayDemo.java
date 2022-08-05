package com.cc.bootstrap.common.demo.time.date;

import java.time.Month;
import java.time.MonthDay;

/**
 * @Description: 月日
 * MonthDay类表示特定月份的 某一天，例如 1 月 1 日的元旦。
 * @author: ChenChen
 * @date: 2022/8/4 17:28
 */
public class MonthDayDemo {
    public static void main(String[] args) {
        //下面的示例使用 MonthDay.isValidYear方法来确定 2 月 29 日对于 2010 年是否有效。调用返回false，确认 2010 年不是闰年。
        MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
        boolean validLeapYear = date.isValidYear(2010);

        System.out.println(validLeapYear);
    }
}
