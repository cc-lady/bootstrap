package com.cc.bootstrap.common.demo.time.chrono;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * @Description: 非 ISO 日期转换
 * 本教程不详细讨论 java.time.chrono包。然而，知道这个包提供了几个不基于 ISO 的预定义年表可能很有用，例如日语、回历、民国和泰国佛教。
 * 你也可以使用这个包来创建你自己的年表。
 *
 * 本节向您展示如何在基于 ISO 的日期和采用其他预定义年表之一的日期之间进行转换。
 * @author: ChenChen
 * @date: 2022/8/5 15:59
 */
public class ChronologyDemo {
    //该StringConverter示例将LocalDate转换为ChronoLocalDate再转换回字符串。toString方法采用LocalDate和Chronology的实例，
    //并使用提供的Chronology返回转换后的字符串。DateTimeFormatterBuilder用于构建可用于打印日期的字符串：
    public static String toString(LocalDate localDate, Chronology chrono) {
        if (localDate != null) {
            Locale locale = Locale.getDefault(Locale.Category.FORMAT);
            ChronoLocalDate cDate;
            if (chrono == null) {
                chrono = IsoChronology.INSTANCE;
            }
            try {
                cDate = chrono.date(localDate);
            } catch (DateTimeException ex) {
                System.err.println(ex);
                chrono = IsoChronology.INSTANCE;
                cDate = localDate;
            }
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                            .withLocale(locale)
                            .withChronology(chrono)
                            .withDecimalStyle(DecimalStyle.of(locale));
            String pattern = "M/d/yyyy GGGGG";
            return dateFormatter.format(cDate);
        } else {
            return "";
        }
    }

    //
    public static LocalDate fromString(String text, Chronology chrono) {
        if (text != null && !text.isEmpty()) {
            Locale locale = Locale.getDefault(Locale.Category.FORMAT);
            if (chrono == null) {
                chrono = IsoChronology.INSTANCE;
            }
            String pattern = "M/d/yyyy GGGGG";
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
                    .appendPattern(pattern)
                    .toFormatter()
                    .withChronology(chrono)
                    .withDecimalStyle(DecimalStyle.of(locale));
            TemporalAccessor temporal = df.parse(text);
            ChronoLocalDate cDate = chrono.date(temporal);
            return LocalDate.from(cDate);
        }
        return null;
    }

    //示例中的fromString方法 StringConverter解析包含非 ISO 日期的字符串并返回LocalDate实例。
    public static void main(String[] args) {
        //1.Converting to a Non-ISO-Based Date
        //您可以使用from(TemporalAccessor)方法 将基于 ISO 的日期转换为另一个年表中的日期，例如 JapaneseDate.from(TemporalAccessor)。
        // 如果无法将日期转换为有效实例，此方法将引发DateTimeException 。
        //以下代码将LocalDateTime实例转换为几个预定义的非 ISO 日历日期：
        LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        JapaneseDate jdate     = JapaneseDate.from(date);
        HijrahDate hdate       = HijrahDate.from(date);
        MinguoDate mdate       = MinguoDate.from(date);
        ThaiBuddhistDate tdate = ThaiBuddhistDate.from(date);

        LocalDate date2 = LocalDate.of(1996, Month.OCTOBER, 29);
        System.out.printf("%s%n",
                ChronologyDemo.toString(date2, JapaneseChronology.INSTANCE));
        System.out.printf("%s%n",
                ChronologyDemo.toString(date2, MinguoChronology.INSTANCE));
        System.out.printf("%s%n",
                ChronologyDemo.toString(date2, ThaiBuddhistChronology.INSTANCE));
        System.out.printf("%s%n",
                ChronologyDemo.toString(date2, HijrahChronology.INSTANCE));
//        平成08-10-29
//        民国85-10-29
//        佛历2539-10-29
//        回历17-06-16


        //2.Converting to an ISO-Based Date
//        您可以使用静态 LocalDate.from方法 将非 ISO 日期转换为LocalDate实例，如下例所示：
//        LocalDate 日期 = LocalDate.from(JapaneseDate.now());
//        其他基于时间的类也提供此方法，如果无法转换日期，则会抛出DateTimeException 。
//        示例中的fromString方法 StringConverter解析包含非 ISO 日期的字符串并返回LocalDate实例。
        System.out.printf("%s%n", ChronologyDemo.fromString("10/29/0008 H",
                JapaneseChronology.INSTANCE));
        System.out.printf("%s%n", ChronologyDemo.fromString("10/29/0085 1",
                MinguoChronology.INSTANCE));
        System.out.printf("%s%n", ChronologyDemo.fromString("10/29/2539 B.E.",
                ThaiBuddhistChronology.INSTANCE));
        System.out.printf("%s%n", ChronologyDemo.fromString("6/16/1417 1",
                HijrahChronology.INSTANCE));
//        1996-10-29
//        1996-10-29
//        1996-10-29
//        1996-10-29
    }
}
