package com.cc.bootstrap.common.demo.time.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: 本地日期
 * Date-Time API 提供了四个类，它们专门处理日期信息，而不考虑时间或时区。类名建议使用这些类：LocalDate、YearMonth、MonthDay和Year。
 * LocalDate表示 ISO 日历中 的 年月日，可用于表示没有时间的日期。您可以使用LocalDate来跟踪重要事件，例如出生日期或结婚日期。
 * @author: ChenChen
 * @date: 2022/8/4 17:20
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        //以下示例使用of和with方法创建LocalDate的实例：
        LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
        LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));

        //除了常用方法之外，LocalDate类还提供了获取有关给定日期的信息的 getter 方法。
        //getDayOfWeek 方法返回特定日期所在的星期几。例如，以下代码行返回“MONDAY”：
        DayOfWeek dotw = LocalDate.of(2012, Month.JULY, 9).getDayOfWeek();

        //以下示例使用TemporalAdjuster检索特定日期之后的第一个星期三。
        LocalDate date1 = LocalDate.of(2000, Month.NOVEMBER, 20);
        TemporalAdjuster adj = TemporalAdjusters.next(DayOfWeek.WEDNESDAY);
        LocalDate nextWed1 = date1.with(adj);
        System.out.printf("For the date of %s, the next Wednesday is %s.%n",
                date1, nextWed1);//For the date of 2000-11-20, the next Wednesday is 2000-11-22.

        //Period 和 Duration部分也有使用LocalDate类 的 示例。
    }
}
