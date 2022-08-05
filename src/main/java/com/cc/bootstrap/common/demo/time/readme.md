# 本目录为jdk8 java.time包的学习记录
Java SE 8 版本中引入的日期时间包 java.time提供了日期和时间的综合模型，并在 JSR 310：日期和时间 API下开发。
虽然java.time基于国际标准化组织 (ISO) 日历系统，但也支持常用的全局日历。
这条线索涵盖了使用基于 ISO 的类来表示日期和时间以及操作日期和时间值的基础知识。

## Lesson: Date-Time 
### Date-Time Overview
时间似乎是一个简单的主题；即使是便宜的手表也能提供相当准确的日期和时间。但是，通过仔细检查，您会意识到影响您对时间理解的微妙复杂性和许多因素。例如，对于闰年，在 1 月 31 日加上一个月的结果与其他年份不同。时区也增加了复杂性。
例如，一个国家可能会在短时间内进入和退出夏令时，或者一年不止一次，或者它可能在给定的年份完全跳过夏令时。
Date-Time API 使用 ISO-8601中定义的日历系统作为默认日历。此日历基于公历系统，在全球范围内用作表示日期和时间的事实标准。Date-Time API 中的核心类具有诸如LocalDateTime、ZonedDateTime和OffsetDateTime之类的名称。所有这些都使用 ISO 日历系统。如果您想使用其他日历系统，例如 Hijrah 或 Thai佛教，java.time.chrono包允许您使用预定义的日历系统之一。或者您可以创建自己的。
Date-Time API 使用 Unicode Common Locale Data Repository (CLDR)。该存储库支持世界上的语言，并包含世界上最大的可用语言环境数据集合。此存储库中的信息已本地化为数百种语言。日期时间 API 还使用 时区数据库 (TZDB)。该数据库提供了自 1970 年以来全球每次时区变化的信息，以及自引入该概念以来的主要时区历史。
### Date-Time Design Principles
Date-Time API 是使用多种设计原则开发的。
（1）清除
API 中的方法定义良好，其行为清晰且符合预期。例如，使用null参数值调用 Date-Time 方法通常会触发NullPointerException。
（2）流利
Date-Time API 提供了流畅的接口，使代码易于阅读。因为大多数方法不允许带空值的参数并且不返回空值，所以可以将方法调用链接在一起，并且可以快速理解生成的代码。例如：
（3）今天的 LocalDate = LocalDate.now();
LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
（4）不可变
Date-Time API 中的大多数类都创建了 不可变的对象，这意味着对象创建后就无法修改。要更改不可变对象的值，必须将新对象构造为原始对象的修改副本。这也意味着 Date-Time API 根据定义是线程安全的。这会影响 API，因为用于创建日期或时间对象的大多数方法都以 、 或 为前缀of，from而with不是构造函数，并且没有set方法。例如：
LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
LocalDate firstBirthday = dateOfBirth.plusYears(1);
（5）可扩展
Date-Time API 是可扩展的，只要有可能。例如，您可以定义自己的时间调整器和查询，或构建自己的日历系统。
### The Date-Time Packages
The Date-Time API consists of the primary package, java.time, and four subpackages:

java.time
The core of the API for representing date and time. 
It includes classes for date, time, date and time combined, time zones, instants, duration, and clocks. These classes are based on the calendar system defined in ISO-8601, and are immutable and thread-safe.
表示日期和时间的 API 的核心。它包括日期、时间、日期和时间组合、时区、瞬间、持续时间和时钟的类。这些类基于 ISO-8601 中定义的日历系统，并且是不可变的和线程安全的。
java.time.chrono
The API for representing calendar systems other than the default ISO-8601. You can also define your own calendar system. This tutorial does not cover this package in any detail.
用于表示除默认 ISO-8601 之外的日历系统的 API。您还可以定义自己的日历系统。本教程没有详细介绍这个包。
java.time.format
Classes for formatting and parsing dates and times.
用于格式化和解析日期和时间的类。
java.time.temporal
Extended API, primarily for framework and library writers, allowing interoperations between the date and time classes, querying, and adjustment. Fields (TemporalField and ChronoField) and units (TemporalUnit and ChronoUnit) are defined in this package.
扩展 API，主要用于框架和库编写者，允许日期和时间类之间的互操作、查询和调整。字段（TemporalField和ChronoField）和单位（TemporalUnit和ChronoUnit）在此包中定义。
java.time.zone
Classes that support time zones, offsets from time zones, and time zone rules. If working with time zones, most developers will need to use only ZonedDateTime, and ZoneId or ZoneOffset.
支持时区、时区偏移和时区规则的类。如果使用时区，大多数开发人员将只需要使用ZonedDateTime和ZoneId或ZoneOffset。

### 方法命名约定
Date-Time API 在一组丰富的类中提供了一组丰富的方法。方法名称尽可能在类之间保持一致。例如，许多类提供了一个now方法，该方法捕获与该类相关的当前时刻的日期或时间值。有from方法允许从一个类转换到另一个类。
还有关于方法名称前缀的标准化。由于 Date-Time API 中的大多数类都是不可变的，因此 API 不包含set方法。（不可变对象创建后，其值无法更改。set方法的不可变等价物是with 。）下表列出了常用的前缀：

Prefix	Method  Type	Use
of	    static factory	Creates an instance where the factory is primarily validating the input parameters, not converting them.
创建一个工厂主要验证输入参数的实例，而不是转换它们。
from	static factory	Converts the input parameters to an instance of the target class, which may involve losing information from the input.
将输入参数转换为目标类的实例，这可能涉及丢失输入中的信息。
parse	static factory	Parses the input string to produce an instance of the target class.
解析输入字符串以生成目标类的实例。
format	instance	    Uses the specified formatter to format the values in the temporal object to produce a string.
使用指定的格式化程序格式化临时对象中的值以生成字符串。
get	    instance	    Returns a part of the state of the target object.
返回目标对象的一部分状态。
is	    instance	    Queries the state of the target object.
查询目标对象的状态。
with	instance	    Returns a copy of the target object with one element changed; this is the immutable equivalent to a set method on a JavaBean.
返回目标对象的副本，其中一个元素已更改；这是JavaBean 上的set方法的不可变等价物。
plus	instance	    Returns a copy of the target object with an amount of time added.
返回添加了时间量的目标对象的副本。
minus	instance	    Returns a copy of the target object with an amount of time subtracted.
返回减去时间量的目标对象的副本。
to	    instance	    Converts this object to another type.
将此对象转换为另一种类型。
at	    instance	    Combines this object with another.
将此对象与另一个对象结合。

### Lesson: Standard Calendar
The core of the Date-Time API is the java.time package. The classes defined in java.time base their calendar system on the ISO calendar, which is the world standard for representing date and time. The ISO calendar follows the proleptic Gregorian rules. The Gregorian calendar was introduced in 1582; in the proleptic Gregorian calendar, dates are extended backwards from that time to create a consistent, unified timeline and to simplify date calculations.
Date-Time API 的核心是 java.time包。java.time中定义的类的日历系统基于 ISO 日历，这是表示日期和时间的世界标准。ISO 日历遵循预测的公历规则。公历于 1582 年推出；在预测的公历中，日期从那个时间向后延伸，以创建一致、统一的时间线并简化日期计算。
This lesson covers the following topics:

Overview
This section compares the concepts of human time and machine time provides a table of the primary temporal-based classes in the java.time package.

#### DayOfWeek and Month Enums
This section discusses the enum that defines the days of the week (DayOfWeek) and the enum that defines months (Month).
本节讨论定义星期几的枚举 ( DayOfWeek ) 和定义月份的枚举 ( Month )。
#### Date Classes
This section shows the temporal-based classes that deal only with dates, without time or time zones. The four classes are LocalDate, YearMonth, MonthDay and Year.
本节显示仅处理日期，没有时间或时区的基于时间的类。这四个类是LocalDate、YearMonth、MonthDay和Year。
#### Date and Time Classes
This section presents the LocalTime and LocalDateTime classes, which deal with time, and date and time, respectively, but without time zones.
本节介绍LocalTime和LocalDateTime类，它们分别处理时间、日期和时间，但没有时区。
#### Time Zone and Offset Classes
This section discusses the temporal-based classes that store time zone (or time zone offset) information, ZonedDateTime, OffsetDateTime, and OffsetTime. The supporting classes, ZoneId, ZoneRules, and ZoneOffset, are also discussed.
时区和偏移类
本节讨论存储时区（或时区偏移）信息的基于时间的类ZonedDateTime、OffsetDateTime和OffsetTime。还讨论了支持类ZoneId、ZoneRules和ZoneOffset 。
#### Instant Class
This section discusses the Instant class, which represents an instantaneous moment on the timeline.
本节讨论Instant类，它表示时间轴上的瞬时时刻。
#### Parsing and Formatting
This section provides an overview of how to use the predefined formatters to format and parse date and time values.
节概述了如何使用预定义的格式化程序来格式化和解析日期和时间值。
#### The Temporal Package
This section presents an overview of the java.time.temporal package, which supports the temporal classes, fields (TemporalField and ChronoField) and units (TemporalUnit and ChronoUnit). This section also explains how to use a temporal adjuster to retrieve an adjusted time value, such as "the first Tuesday after April 11", and how to perform a temporal query.
本节概述java.time.temporal包，它支持时间类、字段（TemporalField和ChronoField）和单位（TemporalUnit和ChronoUnit）。本节还解释了如何使用时间调整器来检索调整后的时间值，例如“4 月 11 日之后的第一个星期二”，以及如何执行时间查询。
#### Period and Duration
This section explains how to calculate an amount of time, using both the Period and Duration classes, as well as the ChronoUnit.between method.
本节说明如何使用Period和Duration类以及ChronoUnit.between方法计算时间量。
#### Clock
This section provides a brief overview of the Clock class. You can use this class to provide an alternative clock to the system clock.
本节简要概述了Clock类。您可以使用此类为系统时钟提供替代时钟。
#### Non-ISO Date Conversion
This section explains how to convert from a date in the ISO calendar system to a date in a non-ISO calendar system, such as a JapaneseDate or a ThaiBuddhistDate.
本节说明如何将 ISO 日历系统中的日期转换为非 ISO 日历系统中的日期，例如JapaneseDate或ThaiBuddhistDate。
#### Legacy Date-Time Code
This section offers some tips on how to convert older java.util.Date and java.util.Calendar code to the Date-Time API.
本节提供了一些关于如何将较旧的java.util.Date和java.util.Calendar代码转换为 Date-Time API 的提示。
#### Summary
This section provides a summary of the Standard Calendar lesson.
本节提供标准日历课程的摘要。

### 概括
java.time包包含许多您的程序可以用来表示时间和日期的类。这是一个非常丰富的 API。基于 ISO 的日期的关键入口点如下：

Instant类提供时间线的机器视图。
LocalDate、LocalTime和LocalDateTime类提供了日期和时间的人类视图，而无需参考时区。
ZoneId 、ZoneRules和ZoneOffset类描述时区、时区偏移和时区规则。
ZonedDateTime类表示带有时区的日期和时间。OffsetDateTime和OffsetTime类分别表示日期和时间或时间。这些类考虑了时区偏移。
Duration类以秒和纳秒为单位测量时间量。
Period类使用年、月和日来衡量时间量。
其他非 ISO 日历系统可以使用java.time.chrono包来表示。这个包超出了本教程的范围，尽管 非 ISO 日期转换页面提供了有关将基于 ISO 的日期转换为另一个日历系统的信息。

日期时间 API 是作为 Java 社区进程的一部分在 JSR 310 的名称下开发的。有关更多信息，请参阅 JSR 310：日期和时间 API。



