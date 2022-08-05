package com.cc.bootstrap.common.demo.time.temporal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

/**
 * @Description: 时间查询
 * 可以使用预定义查询，也可以自定义查询
 * @author: ChenChen
 * @date: 2022/8/5 14:24
 */
public class TemporalQueryDemo {
    public static void main(String[] args) {
        // 1. Predefined Queries 预定义查询
        //TemporalQueries类（注意复数）提供了几个预定义的查询，包括在应用程序无法识别基于时间的对象的类型时有用的方法 。
        // 与调整器一样，预定义查询被定义为静态方法，并设计为与 静态导入语句一起使用。
        //例如，精度查询返回特定基于时间的对象可以返回 的 最小ChronoUnit 。以下示例对几种基于时间的对象使用精度查询：
        TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
        System.out.printf("LocalDate precision is %s%n",
                LocalDate.now().query(query));
        System.out.printf("LocalDateTime precision is %s%n",
                LocalDateTime.now().query(query));
        System.out.printf("Year precision is %s%n",
                Year.now().query(query));
        System.out.printf("YearMonth precision is %s%n",
                YearMonth.now().query(query));
        System.out.printf("Instant precision is %s%n",
                Instant.now().query(query));

//        LocalDate precision is Days
//        LocalDateTime precision is Nanos
//        Year precision is Years
//        YearMonth precision is Months
//        Instant precision is Nanos

        TemporalQuery<ZoneId> queryZone = TemporalQueries.zone();
        System.out.printf("LocalDate zone is %s%n",
                LocalDate.now().query(queryZone));
        Instant instant = Instant.parse("2022-08-05T06:30:02Z");
        System.out.printf("instant zone is %s%n",
                instant.query(queryZone));//instant也是没有时区的，只不过表示瞬时
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.printf("zonedDateTime zone is %s%n",
                zonedDateTime.query(queryZone));
        System.out.println("instant = " + instant);//instant = 2022-08-05T06:30:02Z
        System.out.println("zonedDateTime = " + zonedDateTime);//zonedDateTime = 2022-08-05T14:30:02+08:00[Asia/Shanghai]

        TemporalQuery<Chronology> queryChronology = TemporalQueries.chronology();
        System.out.printf("LocalDate Chronology is %s%n",
                LocalDate.now().query(queryChronology));//LocalDate Chronology is ISO
    }
}
