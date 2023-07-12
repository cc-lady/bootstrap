package com.cc.bootstrap.intl.demo.basic;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * @Description: 日期、时间类
 * @author: ChenChen
 * @date: 2023/5/18 14:40
 */
public class Demo4_Date {
    public static void main(String[] args) {
        /*Java原本提供了Date和Calendar类用于处理日期、时间，包括创建日期、时间对象，获取系统当前日期、时间等操作。但Date不仅无法实现国际化，
        而且它对不同的属性也使用了前后矛盾的偏移量，比如月份与小时都是从0开始的，月份中的天数则是从1开始的，年又是从1900开始的，而java.util.Calendar则显得过于复杂，
        从下面的介绍中会看到传统Java对日期、时间处理的不足。Java 8则吸取了Joda-Time库（一个被广泛使用的日期、时间库）的经验，提供了一套全新的日期、时间库。*/

        //1.Date类
        /*Java提供了Date类来处理日期、时间（此处的Date是指java.util包下的Date类，而不是java.sql包下的Date类）, Date对象既包含日期，也包含时间。Date类从JDK 1.0起就开始存在了，但正因为它历史悠久，所以它的大部分构造器、方法都已经过时，不再推荐使用了。
        Date类提供了6个构造器，其中4个Deprecated（Java不再推荐使用，使用不再推荐的构造器时编译器会发出警告信息，并导致程序性能、安全性等方面的问题），剩下的2个构造器如下。
➢Date()：生成一个代表当前日期、时间的Date对象。该构造器在底层调用System.currentTimeMillis()获得long类型整数作为日期参数。
➢Date(long date)：根据指定的long类型整数来生成一个Date对象。该构造器的参数表示创建的Date对象和GMT 1970年1月1日00:00:00之间的时间差，以毫秒作为计时单位。
        与Date构造器相同的是，Date对象的大部分方法也Deprecated了，剩下为数不多的几个方法。
➢boolean after(Date when)：测试该日期是否在指定的日期when之后。
➢boolean before(Date when)：测试该日期是否在指定的日期when之前。
➢long getTime()：返回该时间对应的long类型整数，即从GMT 1970-01-01 00:00:00 到该Date对象之间的时间差，以毫秒作为计时单位。
➢void setTime(long time)：设置该Date对象的时间。
        下面的程序示范了Date类的用法。*/
        Date d1 = new Date();
        // 获取当前时间之后100ms的时间
        Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));

//        总体来说，Date是一个设计相当糟糕的类，因此Java官方推荐尽量少用Date的构造器和方法。如果需要对日期、时间进行加减运算，或者获取指定时间的年、月、日、时、分、秒信息，则可使用Calendar工具类。

        //2.Calendar类
        /*因为Date类在设计上存在一些缺陷，所以Java提供了Calendar类来更好地处理日期、时间。Calendar类是一个抽象类，用于表示日历。
        历史上有着许多种纪年方法，它们的差异实在太大了，比如一个人的生日是“七月七日”，那么一种可能是阳（公）历的七月七日，但也可以是阴（农）历的日期。
        为了统一计时，全世界通常选择最普及、最通用的日历：
        Gregorian Calendar，也就是日常介绍年份时常用的“公元几几年”。
        Calendar类本身是一个抽象类，它是所有日历类的模板，并提供了一些所有日历通用的方法；但它本身不能直接实例化，程序只能创建Calendar子类的实例，
        Java 本身提供了一个GregorianCalendar类，一个代表格里高利日历的子类，它代表了通常所说的公历。
        当然，你也可以创建自己的Calendar子类，然后将它作为Calendar对象使用（这就是多态）。因为篇幅关系，本章不会详细介绍如何扩展Calendar子类，读者可通过互联网查看Calendar各子类的源码来学习。
        Calendar 类是一个抽象类，所以不能使用构造器来创建Calendar 对象。但它提供了几个静态getInstance()方法来获取Calendar对象，这些方法根据TimeZone、Locale类来获取特定的Calendar，如果不指定TimeZone、
        Locale，则使用默认的TimeZone、Locale来创建Calendar。*/
//        Calendar与Date都是表示日期的工具类，它们直接可以自由转换，如下面的代码所示。
        // 创建一个默认的Calendar对象
        Calendar calendar = Calendar.getInstance();
        // 从Calendar 对象中取出Date 对象
        Date date = calendar.getTime();
        // 通过Date对象获得对应的Calendar对象
        // 由于Calendar/GregorianCalendar没有构造函数可以接收Date对象
        // 所以必须先获得一个Calendar实例，然后调用其setTime()方法
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        /*Calendar类提供了大量访问和修改日期、时间的方法，常用的方法如下。
➢void add(int field, int amount)：根据日历的规则，为给定的日历字段添加或减去指定的时间量。
➢int get(int field)：返回指定的日历字段的值。
➢int getActualMaximum(int field)：返回指定的日历字段可能拥有的最大值。例如月，最大值为11。
➢int getActualMinimum(int field)：返回指定的日历字段可能拥有的最小值。例如月，最小值为0。
➢void roll(int field, int amount)：与add()方法类似，区别在于，当加上amount后超过了该字段所能表示
        的最大范围时，也不会向上一个字段进位。
➢void set(int field, int value)：将给定的日历字段设置为给定值。
➢void set(int year, int month, int date)：设置Calendar对象的年、月、日3个字段的值。
➢void set(int year, int month, int date, int hourOfDay, int minute, int second)：设置Calendar对象的年、月、日、时、分、秒6个字段的值。
        上面的很多方法都需要一个int类型的field参数，field是Calendar类的类变量，如Calendar.YEAR、Calendar.MONTH等分别代表年、月、日、小时、分钟、
        秒等时间字段。需要指出的是，Calendar.MONTH字段代表月份，月份的起始值不是1，而是0，比如要设置8月，用7而不是8。如下程序示范了Calendar类的常规用法。 */
        Calendar c = Calendar.getInstance();
        // 取出年
        System.out.println(c.get(YEAR));
        // 取出月份
        System.out.println(c.get(MONTH));
        // 取出日
        System.out.println(c.get(DATE));
        // 分别设置年、月、日、小时、分钟、秒
        c.set(2003, 10, 23, 12, 32, 23); // 2003-11-23 12:32:23
        System.out.println(c.getTime());
        // 将Calendar的年前推1年
        c.add(YEAR, -1); // 2002-11-23 12:32:23
        System.out.println(c.getTime());
        // 将Calendar的月前推8个月
        c.roll(MONTH, -8); // 2002-03-23 12:32:23
        System.out.println(c.getTime());

        //对Calendar类还有如下几个注意点。
        /*1.add与roll的区别
        add(int field, int amount)的功能非常强大，其主要用于改变Calendar的特定字段的值。如果需要增加某字段的值，则让amount为正数；如果需要减少某字段的值，则让amount为负数即可。
        add(int field, int amount)有如下两条规则。
➢当被修改的字段超出它允许的范围时，会发生进位，即上一级字段也会增大。例如：*/
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2003, 7, 23, 0, 0, 0); // 2003-8-23
        cal1.add(MONTH, 6); // 2003-8-23 => 2004-2-23

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2003, 7, 31, 0, 0, 0); // 2003-8-31
// 因为进位后月份改为2月，2月没有31日，自动变成29日
        cal2.add(MONTH, 6); // 2003-8-31 => 2004-2-29

        /*2．设置Calendar的容错性
        当调用Calendar 对象的set()方法来改变指定的时间字段的值时，有可能传入一个不合法的参数。例如，为MONTH字段设置13，这将会导致怎样的结果呢？看如下程序。
        Calendar 有两种解释日历字段的模式：lenient 模式和non-lenient 模式。当 Calendar处于lenient 模式时，每个时间字段都可接受超出它允许范围的值；
        当 Calendar 处于non-lenient模式时，如果为某个时间字段设置的值超出了它允许的取值范围，程序将会抛出异常。*/
        Calendar cal = Calendar.getInstance();
        // 结果是YEAR字段加1, MONTH字段为1（2月）
        cal.set(MONTH, 13);  // ①
        System.out.println(cal.getTime());
        // 关闭容错性
        cal.setLenient(false);
        // 导致运行时异常
        cal.set(MONTH, 13);  // ②
        System.out.println(cal.getTime());

        /*3.set()方法延迟修改
        set(f, value)方法将日历字段f更改为value。此外，它还设置了一个内部成员变量，以指示日历字段f已经被更改。尽管日历字段 f 是立即被更改的，
        但该Calendar所代表的时间却不会立即被修改，直到下次调用get()、getTime()、getTimeInMillis()、add() 或roll()时才会重新计算日历的时间。
        这被称为set()方法延迟修改。采用延迟修改的优势是，多次调用set()，不会触发多次不必要的计算（需要计算出一个代表实际时间的long类型整数）。
        下面的程序演示了set()方法延迟修改的效果*/
        Calendar cal11 = Calendar.getInstance();
        cal11.set(2003, 7, 31);  // 2003-8-31
        // 将月份设为9，但9月31日不存在
        // 如果立即修改，系统将会把cal自动调整到10月1日
        cal11.set(MONTH, 8);
        // 下面的代码输出10月1日
        // System.out.println(cal.getTime());   // ①
        // 设置DATE字段为5
        cal.set(DATE, 5);   // ②
        System.out.println(cal.getTime());   // ③

        //3.Java 17增强的新式日期、时间包
       /* Java 8专门新增了一个java.time包，该包下包含了如下常用的类。
➢Clock：该类用于获取指定时区的当前日期、时间。该类可取代System类的currentTimeMillis()方法，而且提供了更多方法来获取当前日期、时间。该类提供了大量静态方法来获取Clock对象。
➢InstantSource:Java 17引入的规范性接口，它是一个代表可获取时刻（Instant）的通用接口，通过切换使用该接口的不同实现类，能以可插拔的方式来获取当前时刻。Clock就是该接口的实现类。
➢Duration：该类代表持续时间。该类可以非常方便地获取一段时间。
➢Instant：该类代表一个具体的时刻，可以精确到纳秒。该类提供了静态的now()方法来获取当前时刻，也提供了静态的now(Clock clock)方法来获取clock对应的时刻。
        此外，它还提供了一系列minusXxx()方法在当前时刻的基础上减去一段时间，也提供了plusXxx()方法在当前时刻的基础上加上一段时间。
➢LocalDate：该类代表不带时区的日期，例如2007-12-03。该类提供了静态的now()方法来获取当前日期，也提供了静态的now(Clock clock)方法来获取clock对应的日期。
        此外，它还提供了minusXxx()方法在当前日期的基础上减去几年、几月、几周或几日等，也提供了plusXxx()方法在当前日期的基础上加上几年、几月、几周或几日等。
➢LocalTime：该类代表不带时区的时间，例如10:15:30。该类提供了静态的now()方法来获取当前时间，也提供了静态的now(Clock clock)方法来获取clock 对应的时间。
        此外，它还提供了minusXxx()方法在当前日期的基础上减去几小时、几分、几秒等，也提供了plusXxx()方法在当前日期的基础上加上几小时、几分、几秒等。
➢LocalDateTime：该类代表不带时区的日期、时间，例如2007-12-03T10:15:30。该类提供了静态的now()方法来获取当前日期、时间，也提供了静态的now(Clock clock)方法
    来获取clock对应的日期、时间。此外，它还提供了minusXxx()方法在当前日期的基础上减去几年、几月、几日、几小时、几分、几秒等，也提供了plusXxx()方法在当前日期、时间的基础上加上几
        年、几月、几日、几小时、几分、几秒等。
➢MonthDay：该类仅代表月日，例如--04-12。该类提供了静态的now()方法来获取当前月日，也提供了静态的now(Clock clock)方法来获取clock对应的月日。
➢Year：该类仅代表年，例如2014。该类提供了静态的now()方法来获取当前年份，也提供了静态的now(Clock clock)方法来获取clock对应的年份。此外，它还提供了minusYears()方法在当前年份的基础上减去几年，也提供了plusYears()方法在当前年份的基础上加上几年。
➢YearMonth：该类仅代表年月，例如2014-04。该类提供了静态的now()方法来获取当前年月，也提供了静态的now(Clock clock)方法来获取clock对应的年月。此外，它还提供了minusXxx()方法在当前年月的基础上减去几年、几月，也提供了plusXxx()方法在当前年月的基础上加上几年、几月。
➢ZonedDateTime：该类代表一个时区化的日期、时间。
➢ZoneId：该类代表一个时区。
➢DayOfWeek：这是一个枚举类，定义了周日到周六的枚举值。
➢Month：这也是一个枚举类，定义了一月到十二月的枚举值。
        下面通过一个简单的程序来示范这些类的用法。*/
        // -----下面是关于Clock的用法-----
        // 获取当前Clock
        Clock clock = Clock.systemUTC();
        // 通过Clock获取当前时刻
        System.out.println("当前时刻为：" + clock.instant());
        // 获取clock对应的毫秒数，与System.currentTimeMillis()的输出相同
        System.out.println(clock.millis());
        System.out.println(System.currentTimeMillis());
        // 使用InstantSource来获取当前时刻
//        System.out.println("当前时刻：" + InstantSource.system().instant());
        // -----下面是关于Duration的用法-----
        Duration d = Duration.ofSeconds(6000);
        System.out.println("6000秒相当于" + d.toMinutes() + "分");
        System.out.println("6000秒相当于" + d.toHours() + "小时");
        System.out.println("6000秒相当于" + d.toDays() + "天");
        // 在clock的基础上增加6000秒，返回新的Clock
        Clock clock2 = Clock.offset(clock, d);
        // 可以看到clock2与clock1相差1小时40分
        System.out.println("当前时刻加6000秒为：" +clock2.instant());
        // -----下面是关于Instant的用法-----
        // 获取当前时间
        Instant instant = Instant.now();
        System.out.println(instant);
        // instant添加6000秒（即100分钟），返回新的Instant
        Instant instant2 = instant.plusSeconds(6000);
        System.out.println(instant2);
        // 根据字符串解析Instant对象
        Instant instant3 = Instant.parse("2014-02-23T10:12:35.342Z");
        System.out.println(instant3);
        // 在instant3的基础上增加5小时4分钟
        Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
        System.out.println(instant4);
        // 获取instant4的5天以前的时刻
        Instant instant5 = instant4.minus(Duration.ofDays(5));
        System.out.println(instant5);
        // -----下面是关于LocalDate的用法-----
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        // 获得2014年的第146天
        localDate = LocalDate.ofYearDay(2014, 146);
        System.out.println(localDate); // 2014-05-26
        // 设置为2014年5月21日
        localDate = LocalDate.of(2014, Month.MAY, 21);
        System.out.println(localDate); // 2014-05-21
        // -----下面是关于LocalTime的用法-----
        // 获取当前时间
        LocalTime localTime = LocalTime.now();
        // 设置为22点33分
        localTime = LocalTime.of(22, 33);
        System.out.println(localTime); // 22:33
        // 返回一天中的第5503秒
        localTime = LocalTime.ofSecondOfDay(5503);
        System.out.println(localTime); // 01:31:43
        // -----下面是关于localDateTime的用法-----
        // 获取当前日期、时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 当前日期、时间加上25小时3分钟
        LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);
        System.out.println("当前日期、时间的25小时3分钟之后：" + future);
        // -----下面是关于Year、YearMonth、MonthDay的用法示例-----
        Year year = Year.now(); // 获取当前年份
        System.out.println("当前年份：" + year); // 输出当前年份
        year = year.plusYears(5); // 当前年份再加5年
        System.out.println("当前年份再加5年：" + year);
        // 根据指定月份获取YearMonth
        YearMonth ym = year.atMonth(10);
        System.out.println("year年10月：" + ym); // 输出XXXX-10, XXXX代表当前年份
        // 当前年月再加5年、减3个月
        ym = ym.plusYears(5).minusMonths(3);
        System.out.println("year年10月再加5年、减3个月：" + ym);
        MonthDay md = MonthDay.now();
        System.out.println("当前月日：" + md); // 输出--XX-XX，代表几月几日
        // 设置为5月23日
        MonthDay md2 = md.with(Month.MAY).withDayOfMonth(23);
        System.out.println("5月23日为：" + md2); // 输出--05-23


    }
}
