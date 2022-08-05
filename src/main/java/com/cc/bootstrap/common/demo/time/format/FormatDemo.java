package com.cc.bootstrap.common.demo.time.format;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @Description: 格式化示例
 *
 * @author: ChenChen
 * @date: 2022/8/5 11:06
 */
public class FormatDemo {
    public static void main(String[] args) {
//        format(DateTimeFormatter)方法使用指定格式 将 基于时间的对象转换为字符串表示形式。示例中的以下代码 使用“MMM d yyy hh:mm a”
//        格式转换ZonedDateTimeFlight的实例。日期的定义方式与前面的解析示例相同，但此模式还包括小时、分钟以及上午和下午组件。
        ZoneId leavingZone = ZoneId.of("Asia/Shanghai");
        LocalDateTime leaving = LocalDateTime.of(2022, Month.AUGUST, 5, 11, 8);
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
            String out = departure.format(format);
            System.out.printf("LEAVING:  %s (%s)%n", out, leavingZone);
//            LEAVING:  八月 5 2022  11:08 上午 (Asia/Shanghai)
        }
        catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }

        // 'date' has been successfully parsed
        LocalDate localDate = LocalDate.now();
        Locale locale = Locale.getDefault(Locale.Category.FORMAT);
        Chronology chrono = IsoChronology.INSTANCE;
        ChronoLocalDate cDate= chrono.date(localDate);

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(locale)
                        .withChronology(chrono)
                        .withDecimalStyle(DecimalStyle.of(locale));
        String format = dateFormatter.format(cDate);
        System.out.println("now = " + localDate + " format = " + format);
        // now = 2022-08-05 format = 2022年8月5日 星期五
    }
}
