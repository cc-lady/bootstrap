package com.cc.bootstrap.common.demo.time.date_time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

/**
 * @Description: LocalDateTime
 * 处理日期和时间且没有时区的类是 LocalDateTime，它是 Date-Time API 的核心类之一。此类用于表示日期（月-日-年）和时间（小时-分钟-秒-纳秒），
 * 实际上是LocalDate和LocalTime的组合。此类可用于表示特定事件，例如美洲杯挑战者系列赛路易威登杯总决赛的第一场比赛，
 * 比赛于 2013 年 8 月 17 日下午 1:10 开始。
 * 请注意，这意味着下午 1:10在当地时间。要包含时区，您必须使用ZonedDateTime或OffsetDateTime，如 时区和偏移类中所述。
 * @author: ChenChen
 * @date: 2022/8/4 17:38
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        //除了每个基于时间的类都提供的now方法之外，LocalDateTime类还有各种方法（或以 of 为前缀的方法）来创建LocalDateTime的实例。
        //有一个from方法可以将实例从另一种时间格式转换为LocalDateTime实例。还有一些方法可以增加或减少小时、分钟、天、周和月。
        //以下示例显示了其中一些方法。日期时间表达式以粗体显示：
        System.out.printf("now: %s%n", LocalDateTime.now());//2022-08-04T17:40:37.990

        System.out.printf("Apr 15, 1994 @ 11:30am: %s%n",
                LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));//Apr 15, 1994 @ 11:30am: 1994-04-15T11:30

        System.out.printf("now (from Instant): %s%n",
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));//now (from Instant): 2022-08-04T17:40:38.005

        System.out.printf("6 months from now: %s%n",
                LocalDateTime.now().plusMonths(6));//6 months from now: 2023-02-04T17:40:38.005

        System.out.printf("6 months ago: %s%n",
                LocalDateTime.now().minusMonths(6));//6 months ago: 2022-02-04T17:40:38.005
    }
}
