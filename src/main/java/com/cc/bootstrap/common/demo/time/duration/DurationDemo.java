package com.cc.bootstrap.common.demo.time.duration;

import java.time.Duration;
import java.time.Instant;

/**
 * @Description: 持续时间，期间
 * 当您编写代码来指定时间量时，请使用最能满足您需求的类或方法： Duration类、 Period类或 ChronoUnit.between方法。
 * Duration使用基于时间的值（秒、纳秒）测量时间量。
 * Period使用基于日期的值（年、月、日）。
 * 注意：一天 的持续时间正好是24 小时。一天的Period添加到ZonedDateTime时，可能会因时区而异。例如，如果它发生在夏令时的第一天或最后一天。
 *
 * Duration最适合测量基于机器 的时间的情况，例如使用Instant对象的代码。Duration对象以秒或纳秒为单位进行测量，并且不使用基于日期的构造，
 * 例如年、月和日，尽管该类提供了转换为日、小时和分钟的方法。如果Duration的结束点在开始点之前创建，则Duration可以具有负值。
 * @author: ChenChen
 * @date: 2022/8/5 15:09
 */
public class DurationDemo {
    public static void main(String[] args) {
        // 以下代码以纳秒为单位计算两个瞬间之间的持续时间：
        Instant t1 = Instant.parse("2022-08-05T15:14:00Z");
        Instant t2 = Instant.parse("2022-08-05T15:15:00Z");
        Duration duration = Duration.between(t1, t2);
        System.out.println(duration);//PT1M
        long ns = duration.getSeconds();
        System.out.println("t1 to t2 seconds = " + ns);// 60s

        // 以下代码将 10 秒添加到Instant：
        Instant start = Instant.parse("2022-08-05T15:14:00Z");
        Duration gap = Duration.ofSeconds(10);
        Instant later = start.plus(gap);
        System.out.println("t1 add 10 seconds = " + later);// 2022-08-05T15:14:10Z
    }
}
