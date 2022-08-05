package com.cc.bootstrap.common.demo.time.duration;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @Description: 时期
 * 要使用基于日期的值（年、月、日）定义时间量，请使用 Period类。
 * Period类提供了各种get方法，例如 getMonths、 getDays和 getYears，以便您可以从 period 中提取时间量。
 *
 * 总时间段由所有三个单位共同表示：月、日和年。要显示以单个时间单位（例如天）测量的时间量，可以使用ChronoUnit.between方法。
 * @author: ChenChen
 * @date: 2022/8/5 15:30
 */
public class PeriodDemo {
    public static void main(String[] args) {
        // 以下代码报告您的年龄，假设您出生于 1993 年 6 月 11 日。Period类用于确定以年、月和日为单位的时间。
        // 使用ChronoUnit.between方法确定同一时期（以天为单位），并显示在括号中：
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1993, Month.JUNE, 11);

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");

        // 要计算距离您下一个生日还有多长时间，您可以使用 Birthday示例中的以下代码。Period类用于确定以月和日为单位的值。
        // ChronoUnit.between方法返回总天数的值并显示在括号中。
        LocalDate nextBDay = birthday.withYear(today.getYear());

        //If your birthday has occurred this year already, add 1 to the year.
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        Period nextPriod = Period.between(today, nextBDay);
        long period = ChronoUnit.DAYS.between(today, nextBDay);
        System.out.println("There are " + nextPriod.getMonths() + " months, and " +
                nextPriod.getDays() + " days until your next birthday. (" +
                period + " total)");

//        以上这些计算不考虑时区差异。例如，如果您出生在澳大利亚，但目前居住在班加罗尔，这会稍微影响您准确年龄的计算。
//        在这种情况下，将Period与ZonedDateTime类结合使用。当您将Period添加到ZonedDateTime时，会观察到时间差异。
    }
}
