package com.cc.bootstrap.common.demo.time.zone;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * @Description: Instant 瞬时
 * Date-Time API 的核心类之一是 Instant类，它表示时间轴上一纳秒的开始。此类对于生成表示机器时间的时间戳很有用。
 * 从Instant类返回的值从 1970 年 1 月 1 日 ( 1970-01-01T00:00:00Z ) 的第一秒开始计算时间，也称为 EPOCH。
 * 发生在 epoch 之前的瞬间具有负值，发生在 epoch 之后的瞬间具有正值。
 * Instant类 提供的其他常量是MIN，表示可能的最小（远过去）时刻，和 MAX，表示最大（远将来）时刻。
 * 在Instant上 调用toString会产生如下输出：
 * 2013-05-30T23:38:23.085Z
 * 此格式遵循 用于表示日期和时间的ISO-8601标准。
 *
 * ZonedDateTime或OffsetTimeZone对象都可以转换为Instant对象，因为每个对象都映射到时间轴上的确切时刻。
 * 然而，反过来是不正确的。要将Instant对象转换为ZonedDateTime或OffsetDateTime对象，需要提供时区或时区偏移信息。
 *
 * 注意Instant本身如果没有设置时区，是没有时区的，TemporalQueryDemo可以验证。但是它的时间显示和标准时间一致。
 * @author: ChenChen
 * @date: 2022/8/5 10:29
 */
public class InstantDemo {
    public static void main(String[] args) {
        Instant timestamp = Instant.now();
        System.out.println(timestamp);//2022-08-05T02:49:01.419Z

        // Instant类提供了多种操作Instant 的方法。加减时间有加减法。以下代码将当前时间增加 1 小时：
        Instant oneHourLater = Instant.now().plus(1, ChronoUnit.HOURS);
        System.out.println(oneHourLater);//2022-08-05T03:49:01.466Z

        // 有一些比较瞬间的方法，例如 isAfter和 isBefore。until方法返回两个Instant对象之间存在的 时间。
        // 以下代码行报告自 Java 纪元开始以来已经发生了多少秒。
        long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
        System.out.println("纪元以来多少秒" + secondsFromEpoch);//纪元以来多少秒1659667741

        Instant instant = Instant.ofEpochSecond(secondsFromEpoch);
        System.out.println(instant);//这个显示值和timestamp应该一致 2022-08-05T02:49:01.419Z

        // Instant 类不适用于人类时间单位，例如年、月或日。如果您想以这些单位执行计算，您可以通过将Instant与时区绑定来将Instant转换为另一个类，
        // 例如LocalDateTime或ZonedDateTime 。然后，您可以访问所需单位的值。
        // 以下代码使用ofInstant方法和默认时区将Instant转换为LocalDateTime对象 ，然后以更易读的形式打印出日期和时间：
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());
        System.out.println(ldt);//2022-08-05T10:49:01.419
    }
}
