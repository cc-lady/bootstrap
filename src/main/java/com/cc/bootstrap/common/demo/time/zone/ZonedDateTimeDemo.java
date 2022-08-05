package com.cc.bootstrap.common.demo.time.zone;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 分区日期时间
 * 日期时间类
 * Date-Time API 提供了三个使用时区的基于时间的类：
 * ZonedDateTime处理具有相应时区的日期和时间，该时区与格林威治/UTC 有时区偏移。
 * OffsetDateTime处理具有相对于格林威治/UTC 的相应时区偏移的日期和时间，没有时区 ID。
 * OffsetTime处理时间与格林威治/UTC 的相应时区偏移量，没有时区 ID。
 * 你什么时候会使用OffsetDateTime而不是ZonedDateTime？如果您正在编写复杂的软件来模拟其基于地理位置的日期和时间计算规则，
 * 或者如果您将时间戳存储在仅跟踪格林威治/UTC 时间的绝对偏移量的数据库中，那么您可能需要使用OffsetDateTime .
 * 此外，XML 和其他网络格式将日期时间传输定义为OffsetDateTime或OffsetTime。
 *
 * 重要：
 * 尽管所有三个类都保持与格林威治/UTC 时间的偏移量，但只有ZonedDateTime使用 ZoneRules （ java.time.zone包的一部分）
 * 来确定特定时区的偏移量如何变化。例如，大多数时区在将时钟向前移动到夏令时时会出现间隔（通常为 1 小时），
 * 而在将时钟向后移动到标准时间和重复转换前的最后一小时时会出现时间重叠。ZonedDateTime类可以适应这种情况，
 * 而OffsetDateTime和OffsetTime类不能访问ZoneRules。
 *
 * ZonedDateTime 类实际上结合了 LocalDateTime类和 ZoneId类。
 * 它用于表示带有时区（地区/城市，例如Europe/Paris ）的完整日期（年、月、日）和时间（小时、分钟、秒、纳秒）。
 * @author: ChenChen
 * @date: 2022/8/5 9:29
 */
public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        // 示例中的以下代码 Flight将从旧金山飞往东京的航班的起飞时间定义为美国/洛杉矶时区的ZonedDateTime 。
        // withZoneSameInstant和plusMinutes方法用于创建 ZonedDateTime 的实例，该实例表示在650 分钟飞行后预计到达东京的时间。
        // ZoneRules.isDaylightSavings方法确定航班抵达东京时是否为夏令时。
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");

// Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
        LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            String out1 = departure.format(format);
            System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }

// Flight is 10 hours and 50 minutes, or 650 minutes
        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
                .plusMinutes(650);

        try {
            String out2 = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", arrival);
            throw exc;
        }

        if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant()))
            System.out.printf("  (%s daylight saving time will be in effect.)%n",
                    arrivingZone);
        else
            System.out.printf("  (%s standard time will be in effect.)%n",
                    arrivingZone);
    }
}
