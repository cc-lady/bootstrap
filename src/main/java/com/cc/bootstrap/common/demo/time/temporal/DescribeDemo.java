package com.cc.bootstrap.common.demo.time.temporal;

/**
 * @Description: 有关常量和枚举
 * @author: ChenChen
 * @date: 2022/8/5 11:28
 */
public class DescribeDemo {
//    java.time.temporal 包提供了支持日期和时间代码，特别是日期和时间计算的接口、类和枚举的集合。
//
//    这些接口旨在用于最低级别。典型的应用程序代码应该根据具体类型声明变量和参数，例如LocalDate或ZonedDateTime，而不是根据Temporal接口。这与声明String类型的变量完全相同，而不是CharSequence类型的变量。
//
//    Temporal 和 java.time.temporal.TemporalAccessor
//    Temporal接口提供了用于访问基于时间的对象 的 框架，并由基于时间的类实现，例如Instant、LocalDateTime和ZonedDateTime。此接口提供了添加或减去时间单位的方法，使基于时间的算术在各种日期和时间类中变得容易且一致。TemporalAccessor接口提供只读版本的Temporal 接口。
//
//    Temporal和TemporalAccessor对象都是根据字段定义的，如 TemporalField 接口中指定 的那样。
//    ChronoField 枚举是 java.time.temporal.TemporalField 接口的具体实现，并提供了一组丰富的已定义常量，例如DAY_OF_WEEK、MINUTE_OF_HOUR和MONTH_OF_YEAR。
//
//    这些字段的单位由 TemporalUnit接口指定。ChronoUnit枚举实现了TemporalUnit接口。字段ChronoField.DAY_OF_WEEK是ChronoUnit.DAYS和ChronoUnit.WEEKS的组合。ChronoField和ChronoUnit枚举将在以下部分中讨论。
//
//    Temporal接口 中基于算术的方法需要根据TemporalAmount值定义的参数 。Period和Duration类（在 Period 和 Duration 中讨论 ）实现了TemporalAmount接口。
//
//    ChronoField 和 IsoFields
//    实现TemporalField接口 的 ChronoField枚举提供了一组丰富的常量来访问日期和时间值。一些例子是CLOCK_HOUR_OF_DAY、NANO_OF_DAY和DAY_OF_YEAR。此枚举可用于表示时间的概念方面，例如一年中的第三周、一天中的第 11 个小时或每月的第一个星期一。当遇到未知类型的Temporal时，可以使用 TemporalAccessor.isSupported(TemporalField)方法来确定Temporal是否支持特定字段。以下代码行返回false，表示LocalDate不支持ChronoField.CLOCK_HOUR_OF_DAY：
//
//    boolean isSupported = LocalDate.now().isSupported(ChronoField.CLOCK_HOUR_OF_DAY);
//    特定于 ISO-8601 日历系统的附加字段在 IsoFields类中定义。以下示例显示如何使用ChronoField和IsoFields获取字段的值：
//
//            time.get(ChronoField.MILLI_OF_SECOND)
//    int qoy = date.get(IsoFields.QUARTER_OF_YEAR);
//    另外两个类定义了可能有用的附加字段， WeekFields和 JulianFields。
//
//    计时单元
//    ChronoUnit 枚举实现了TemporalUnit接口，并提供了一组基于日期和时间的标准单位，从毫秒到千年。请注意，并非所有类都支持所有ChronoUnit对象。例如，Instant类不支持ChronoUnit.MONTHS或ChronoUnit.YEARS。Date-Time API 中的类包含可用于验证类是否支持特定时间单位的isSupported(TemporalUnit)方法。以下对isSupported的调用返回false，确认Instant类不支持ChronoUnit.DAYS。
//
//    Instant Instant = Instant.now();
//    boolean isSupported = instant.isSupported(ChronoUnit.DAYS);
}
