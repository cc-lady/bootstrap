package com.cc.bootstrap.common.demo.time.legacy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Description: 旧版日期时间代码
 * 在 Java SE 8 发布之前，Java 日期和时间机制由 java.util.Date、 java.util.Calendar和
 * java.util.TimeZone类及其子类（例如 java.util.GregorianCalendar ）提供。这些类有几个缺点，包括：
 * Calendar类不是类型安全的。
 * 因为这些类是可变的，所以它们不能在多线程应用程序中使用。
 * 由于月份数异常和缺乏类型安全性，应用程序代码中的错误很常见。
 *
 * 与旧代码的互操作性
 * 也许您有使用java.util日期和时间类的遗留代码，并且您希望利用java.time功能对您的代码进行最少的更改。
 *
 * JDK 8 版本中添加了几种允许在java.util和java.time对象之间进行转换的方法：
 *
 * Calendar.toInstant()将Calendar对象转换为Instant。
 * GregorianCalendar.toZonedDateTime()将GregorianCalendar实例转换为ZonedDateTime。
 * GregorianCalendar.from(ZonedDateTime)使用ZonedDateTime实例创建一个GregorianCalendar对象。
 * Date.from(Instant)从Instant创建Date对象。
 * Date.toInstant()将Date对象转换为Instant。
 * TimeZone.toZoneId()将TimeZone对象转换为ZoneId。
 * @author: ChenChen
 * @date: 2022/8/5 16:07
 */
public class LegacyDemo {
    public static void main(String[] args) {
        //以下示例将Calendar实例转换为ZonedDateTime实例。请注意，必须提供时区才能从Instant转换为ZonedDateTime：
        Calendar now = Calendar.getInstance();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
        System.out.println(now);
        System.out.println(zonedDateTime);//2022-08-05T16:10:14.876+08:00[Asia/Shanghai]

        //以下示例显示了Date和Instant之间的转换：
        Instant inst = new Date().toInstant();
        Date newDate = Date.from(inst);
        System.out.println(inst);//2022-08-05T08:11:28.972Z
        System.out.println(newDate);//Fri Aug 05 16:11:28 CST 2022

        //以下示例从GregorianCalendar转换为ZonedDateTime，然后从ZonedDateTime转换为GregorianCalendar。
        //其他基于时间的类是使用ZonedDateTime实例创建的：
        GregorianCalendar cal = new GregorianCalendar();

        TimeZone tz = cal.getTimeZone();
        int tzoffset = cal.get(Calendar.ZONE_OFFSET);

        ZonedDateTime zdt = cal.toZonedDateTime();
        System.out.println(zdt);//2022-08-05T16:13:32.243+08:00[Asia/Shanghai]
        GregorianCalendar newCal = GregorianCalendar.from(zdt);

        LocalDateTime ldt = zdt.toLocalDateTime();
        LocalDate date = zdt.toLocalDate();
        LocalTime time = zdt.toLocalTime();
        System.out.println(ldt);//2022-08-05T16:13:32.243
        System.out.println(date);//2022-08-05
        System.out.println(time);//16:13:32.243

        //将 java.util 日期和时间功能映射到 java.time
//        因为日期和时间的 Java 实现在 Java SE 8 版本中已经完全重新设计，所以您不能将一种方法换成另一种方法。如果您想使用java.time包提供的丰富功能，
//        最简单的解决方案是使用上一节中列出的toInstant或toZonedDateTime方法。但是，如果您不想使用该方法或者它不足以满足您的需求，那么您必须重写您的日期时间代码。
//
//        概述页面 上介绍的表格 是开始评估哪些java.time类满足您的需求的好地方。
//
//        两个 API 之间没有一一对应的映射关系，但下表让您大致了解java.util日期和时间类中的哪些功能映射到java.time API。
//
//        java.util 功能	java.time 功能	注释
//        java.util.Date	java.time.Instant	Instant和Date类是相似的。每个类：
//        - 表示时间轴上的瞬时时间点 (UTC)
//                - 保持独立于时区的时间
//                - 表示为纪元秒（从 1970-01-01T00:00:00Z 开始）加上纳秒
//        Date.from (Instant)和Date.toInstant()方法允许在这些类之间进行转换。
//        java.util.GregorianCalendar	java.time.ZonedDateTime	ZonedDateTime类是 GregorianCalendar 的替代品。它提供了以下类似的功能。
//        人类时间表示如下：
//        LocalDate：年、月、日
//        LocalTime：小时、分钟、秒、纳秒
//        ZoneId：时区
//                ZoneOffset
//：与 GMT 的当前偏移量GregorianCalendar.from(ZonedDateTime)和GregorianCalendar.to(ZonedDateTime)方法便于转换这些类之间。
//        java.util.TimeZone	java.time.ZoneId或java.time.ZoneOffset	ZoneId类指定时区标识符，并有权访问每个时区使用的规则。
//        ZoneOffset类仅指定与格林威治/UTC 的偏移量。有关详细信息，请参阅 时区和偏移类。
//        日期设置为1970-01-01的GregorianCalendar
//        java.time.LocalTime	在GregorianCalendar实例中将日期设置为 1970-01-01以使用时间组件的代码可以替换为LocalTime的实例。
//        时间设置为00:00 的公历日历。	java.time.LocalDate	在GregorianCalendar实例中将时间设置为 00:00以使用日期组件的代码可以替换为LocalDate的实例。
//        （这种GregorianCalendar方法是有缺陷的，因为由于过渡到夏令时，某些国家/地区每年不会出现一次午夜。）
//        日期和时间格式
//        尽管java.time.format.DateTimeFormatter为格式化日期和时间值提供了强大的机制，
//        但您也可以直接将java.time基于时间的类与java.util.Formatter和String.format一起使用，
//        使用相同的基于模式与java.util日期和时间类一起使用的格式。
    }
}
