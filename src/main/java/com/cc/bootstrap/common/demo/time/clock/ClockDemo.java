package com.cc.bootstrap.common.demo.time.clock;

import java.time.Clock;

/**
 * @Description: 时钟
 * 大多数基于时间的对象提供无参数now()方法，该方法使用系统时钟和默认时区提供当前日期和时间。
 * 这些基于时间的对象还提供了一个单参数now(Clock)方法，允许您传入另一个 Clock。
 *
 * 当前日期和时间取决于时区，对于全球化应用程序，时钟是必要的，以确保使用正确的时区创建日期/时间。
 * 因此，尽管Clock类的使用是可选的，但此功能允许您针对其他时区测试您的代码，或者通过使用时间不会改变的固定时钟来测试您的代码。
 *
 * Clock类是抽象 的，因此您不能创建它的实例。以下工厂方法可用于测试。
 *
 * Clock.offset(Clock, Duration)返回一个偏移指定Duration的时钟。
 * Clock.systemUTC()返回代表格林威治/UTC 时区的时钟。
 * Clock.fixed(Instant, ZoneId)总是返回相同的Instant。对于这个时钟，时间是静止的。
 * @author: ChenChen
 * @date: 2022/8/5 15:43
 */
public class ClockDemo {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(clock.instant());//2022-08-05T07:52:15.900Z

        Clock clock2 = Clock.systemUTC();
        System.out.println(clock2);
        System.out.println(clock2.instant());//2022-08-05T07:52:15.915Z
    }
}
