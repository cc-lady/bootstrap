package com.cc.bootstrap.common.demo.time.zone;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: 偏移日期时间
 * 实际上， OffsetDateTime类将 LocalDateTime类与 ZoneOffset类结合在一起。它用于表示完整的日期（年、月、日）和时间（小时、分钟、秒、纳秒）
 * 与格林威治/UTC 时间（+/- 小时：分钟，例如+06:00或- 08:00）。
 * @author: ChenChen
 * @date: 2022/8/5 9:44
 */
public class OffsetDateTimeDemo {
    public static void main(String[] args) {
        // 以下示例使用OffsetDateTime和TemporalAdjuster.lastDay方法来查找 2013 年 7 月的最后一个星期四。
        // Find the last Thursday in July 2013.
        LocalDateTime localDate = LocalDateTime.of(2022, Month.AUGUST, 30, 0, 0);
        ZoneOffset offset = ZoneOffset.of("+08:00");

        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
        System.out.println(offsetDate);
        System.out.println(offsetDate.getOffset());
        OffsetDateTime lastThursday =
                offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.println(lastThursday);//2022-08-25T00:00+08:00 OffsetDateTime标识了偏移
        System.out.printf("The last Thursday in July 2013 is the %sth.%n",
                lastThursday.getDayOfMonth());

        LocalDateTime localDateTime = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.println(localDateTime);//2022-08-25T00:00
    }
}
