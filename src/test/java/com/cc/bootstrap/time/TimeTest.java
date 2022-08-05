package com.cc.bootstrap.time;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java.time包练习代码
 * @author: ChenChen
 * @date: 2022/8/5 16:19
 */
public class TimeTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TimeTest.class);

    public int[] monthlyLength(int year) {
        LocalDate localDate = LocalDate.of(year, Month.JANUARY, 1);
        Month[] months = Month.values();
        int[] result = new int[months.length];
        for (Month month: months) {
            localDate = localDate.withMonth(month.getValue());
            System.out.println("month " + month + " lengthOfMonth is " + localDate.lengthOfMonth());
            result[month.getValue() - 1] = localDate.lengthOfMonth();
        }
        return result;
    }

    // 写一个例子，列出当年给定月份的所有星期一。
    private List<LocalDate> mondayAt(int year, int month) {
        List<LocalDate> resultList = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, Month.of(month), 1);
        int length = localDate.lengthOfMonth();
        for(int i = 1; i<= length; i++) {
            if(localDate.getDayOfWeek().getValue() == DayOfWeek.MONDAY.getValue()) {
                resultList.add(localDate);
            }
            localDate = localDate.plusDays(1);
        }
        return resultList;
    }

    @Test
    public void test1() {
        // 1. 写一个例子，对于给定的年份，报告该年份中每个月的长度。
        int[] predit = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int[] result = this.monthlyLength(2022);

        for (int i = 0; i< predit.length; i++) {
            TestCase.assertTrue(predit[i] == result[i]);
        }

        // 答案
        int year = 2022;
        Year test = Year.of(year);
        for (Month month : Month.values()) {
            YearMonth ym = YearMonth.of(year, month);
            System.out.printf("%s: %d days%n", month, ym.lengthOfMonth());
        }
    }

    @Test
    public void test2() {
        // 2. 写一个例子，列出当年给定月份的所有星期一。
        int year = 2022;
        int month = 8;
        List<LocalDate> predicts = new ArrayList<>();
        predicts.add(LocalDate.of(year, month, 1));
        predicts.add(LocalDate.of(year, month, 8));
        predicts.add(LocalDate.of(year, month, 15));
        predicts.add(LocalDate.of(year, month, 22));
        predicts.add(LocalDate.of(year, month, 29));
        List<LocalDate> localDates = this.mondayAt(year, month);

        TestCase.assertTrue(predicts.containsAll(localDates) && predicts.size() == localDates.size());

        // 答案
        LocalDate date = Year.now().atMonth(month).atDay(1).
                with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Month mi = date.getMonth();
        while (mi.getValue() == month) {
            System.out.printf("%s%n", date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            mi = date.getMonth();
        }
    }

    @Test
    public void test3() {
        // 3. 写一个例子来测试给定的日期是否出现在 13 号星期五。
        LocalDate localDate = LocalDate.of(2022, 8, 5);
        Boolean isFriday13th = localDate.query(TimeTest::isFriday13th);
        TestCase.assertTrue(!isFriday13th);
    }

    public static Boolean isFriday13th(TemporalAccessor date) {
        int dayOfWeek = date.get(ChronoField.DAY_OF_WEEK);//是否星期五
        int day   = date.get(ChronoField.DAY_OF_MONTH);//是否13号

        // 是否出现在 13 号星期五。
        if ((dayOfWeek == DayOfWeek.FRIDAY.getValue()) && (day == 13))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }


}
