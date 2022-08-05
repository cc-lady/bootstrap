package com.cc.bootstrap.common.demo.time.temporal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: 时间调节器
 * java.time.temporal包中 的 TemporalAdjuster接口提供了采用Temporal值并返回调整值的方法。调整器可以与任何基于时间的类型一起使用。
 * 如果将调整器与ZonedDateTime一起使用，则会计算一个新的日期，以保留原始时间和时区值。
 * TemporalAdjusters类（注意复数）提供了一组预定义 的 调整器，用于查找每月的第一天或最后一天、一年的第一天或最后一天、
 * 每月的最后一个星期三或特定日期后的第一个星期二，举几个例子。预定义的调整器被定义为静态方法，旨在与 静态导入语句一起使用。
 * @author: ChenChen
 * @date: 2022/8/5 11:30
 */
public class TemporalAdjusterDemo {
    public static void main(String[] args) {
        // 下面的示例使用几个TemporalAdjusters方法，结合基于时间的类中定义的with方法，根据 2022 年 8 月 5 日的原始日期计算新日期：
        LocalDate date = LocalDate.of(2022, Month.AUGUST, 5);
        DayOfWeek dotw = date.getDayOfWeek();
        System.out.printf("%s is on a %s%n", date, dotw);

        System.out.printf("first day of Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.printf("first Monday of Month: %s%n",
                date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.printf("last day of Month: %s%n",
                date.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.printf("first day of next Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.printf("first day of next Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.printf("first day of Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfYear()));
//        2022-08-05 is on a FRIDAY
//        first day of Month: 2022-08-01
//        first Monday of Month: 2022-08-01
//        last day of Month: 2022-08-31
//        first day of next Month: 2022-09-01
//        first day of next Year: 2023-01-01
//        first day of Year: 2022-01-01
    }
}
