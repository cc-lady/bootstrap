package com.cc.bootstrap.common.demo.time.temporal;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

/**
 * @Description: 自定义查询
 * 您还可以创建自己的自定义查询。一种方法是使用queryFrom(TemporalAccessor)方法创建一个实现TemporalQuery接口 的类。
 * 该 示例实现了两个自定义查询。
 * @author: ChenChen
 * @date: 2022/8/5 14:43
 */
public class CustomQueryDemo implements TemporalQuery {

    // 第一个自定义查询可以在 实现 TemporalQuery接口的类中找到。
    // queryFrom方法将传入的日期与计划的假期日期进行比较，如果它在这些日期范围内，则返回TRUE 。CheckDateFamilyVacations
    @Override
    public Boolean queryFrom(TemporalAccessor date) {
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day   = date.get(ChronoField.DAY_OF_MONTH);

        // Disneyland over Spring Break
        if ((month == Month.APRIL.getValue()) && ((day >= 3) && (day <= 8)))
            return Boolean.TRUE;

        // Smith family reunion on Lake Saugatuck
        if ((month == Month.AUGUST.getValue()) && ((day >= 8) && (day <= 14)))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    /**
     * @Description 第二个自定义查询在 FamilyBirthdays类中实现。此类提供了一个isFamilyBirthday方法，
     * 该方法将传入的日期与多个生日进行比较，如果匹配则返回TRUE 。
     *
     * 如果传入的日期与其中一个日期相同，则返回 true因为查询只比较月份和日期，
     * 即使 Temporal 类型不同，检查也会成功。
     * @param date
     * @author ChenChen
     * @return java.lang.Boolean
     * @date 2022/8/5 14:51
     */
    public static Boolean isFamilyBirthday(TemporalAccessor date) {
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day   = date.get(ChronoField.DAY_OF_MONTH);

        // Angie's birthday is on April 3.
        if ((month == Month.APRIL.getValue()) && (day == 3))
            return Boolean.TRUE;

        // Sue's birthday is on June 18.
        if ((month == Month.JUNE.getValue()) && (day == 18))
            return Boolean.TRUE;

        // Joe's birthday is on May 29.
        if ((month == Month.MAY.getValue()) && (day == 29))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        // 1.implements TemporalQuery后，实现queryFrom方法即可
        // Invoking the query without using a lambda expression.
        LocalDate localDate = LocalDate.of(2022, Month.APRIL, 8);
        Boolean isFamilyVacation = (Boolean) localDate.query(new CustomQueryDemo());
        System.out.println("localDate now = " + localDate + ", isFamilyVacation = " + isFamilyVacation);

        // 2.FamilyBirthday类不实现TemporalQuery接口，可以用作 lambda 表达式的一部分。

        // Invoking the query using a lambda expression.
        Boolean isFamilyBirthday = localDate.query(CustomQueryDemo::isFamilyBirthday);
        System.out.println("localDate now = " + localDate + ", isFamilyBirthday = " + isFamilyBirthday);
        if (isFamilyVacation.booleanValue() || isFamilyBirthday.booleanValue())
            System.out.printf("%s is an important date!%n", localDate);
        else
            System.out.printf("%s is not an important date.%n", localDate);
    }


}
