package com.cc.bootstrap.common.demo.time.format;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * @Description: 解析和格式化
 * Date-Time API 中基于时间的类提供了用于解析包含日期和时间信息的字符串的解析方法。这些类还提供格式化方法，用于格式化基于时间的对象以供显示。
 * 在这两种情况下，过程是相似的：您向DateTimeFormatter提供一个模式来创建一个格式化程序对象。然后将此格式化程序传递给parse或format方法。
 * DateTimeFormatter类提供了许多 预定义的格式化程序，或者您可以定义自己的 格式化程序。
 * 如果在转换过程中出现问题，则parse和format方法会抛出异常。因此，您的解析代码应该捕获DateTimeParseException错误，
 * 而您的格式代码应该捕获DateTimeException错误。有关异常处理的更多信息，请参阅 捕获和处理异常。
 * DateTimeFormatter类既不可变又是线程安全的 ；它可以（并且应该）在适当的情况下分配给静态常量。
 * 版本说明：  java.time日期时间对象可以直接与java.util.Formatter和String.format一起使用，
 * 方法是使用与传统java.util.Date和java.util.Calendar一起使用的熟悉的基于模式的格式类。
 * @author: ChenChen
 * @date: 2022/8/5 10:51
 */
public class ParseDemo {
    public static void main(String[] args) {
        // LocalDate类中 的单参数 parse(CharSequence)方法使用ISO_LOCAL_DATE格式化程序。要指定不同的格式化程序，
        // 您可以使用两个参数的 parse(CharSequence, DateTimeFormatter)方法。以下示例使用预定义的BASIC_ISO_DATE格式化程序，
        // 它使用19590709格式，表示1959 年 7 月 9 日。
//        String in = "";
//        LocalDate date = LocalDate.parse(in, DateTimeFormatter.BASIC_ISO_DATE);
//        System.out.println(date);

        // 您还可以使用自己的模式定义格式化程序。示例中的以下代码 Parse创建了一个格式化程序，该格式化程序应用了“MMM d yyyy”格式。
        // 此格式指定三个字符表示月份，一位数字表示月份中的某天，四位数字表示年份。
        // 使用此模式创建的格式化程序将识别诸如“Jan 3 2003”或“Mar 23 1994”之类的字符串。但是，要将格式指定为“MMM dd yyyy”，
        // 使用两个字符表示月份中的某天，那么您必须始终使用两个字符，用零填充一位数字日期：“Jun 03 2003”。
        String input = "08 05 2022";
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM dd yyyy");
            LocalDate date = LocalDate.parse(input, formatter);
            System.out.printf("%s%n", date);//2022-08-05
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", input);
            throw exc;      // Rethrow the exception.
        }

        Locale locale = Locale.getDefault(Locale.Category.FORMAT);
        Chronology chrono = IsoChronology.INSTANCE;
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
                .appendPattern(pattern)
                .toFormatter()
                .withChronology(chrono)
                .withDecimalStyle(DecimalStyle.of(locale));
        String text = "2022-08-05";
        TemporalAccessor temporal = df.parse(text);
        ChronoLocalDate cDate = chrono.date(temporal);
        LocalDate localDate = LocalDate.from(cDate);
        System.out.println("text = " + text + " parse = " + localDate);
        //text = 2022-08-05 parse = 2022-08-05
    }
}
