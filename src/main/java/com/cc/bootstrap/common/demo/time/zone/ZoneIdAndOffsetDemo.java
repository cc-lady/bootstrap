package com.cc.bootstrap.common.demo.time.zone;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Description: Date-Time API 提供了两个类用于指定时区或偏移量：
 * ZoneId指定时区标识符并提供在Instant和LocalDateTime之间转换的规则。
 * ZoneOffset指定与格林威治/UTC 时间的时区偏移量。
 * 与格林威治/UTC 时间的偏移通常以整小时定义，但也有例外。
 * @author: ChenChen
 * @date: 2022/8/4 17:48
 */
public class ZoneIdAndOffsetDemo {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //示例中的以下代码 TimeZoneId打印了所有时区的列表，这些时区使用未在整小时内定义的格林威治/UTC 偏移量。
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);//2022-08-04T17:52:33.351

        // Create a List using the set of zones and sort it.
        List<String> zoneList = new ArrayList<String>(allZones);
        Collections.sort(zoneList);

        for (String s : zoneList) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);

            // Write only time zones that do not have a whole hour offset
            // to standard out.
            if (secondsOfHour != 0) {
                System.out.println("zdt = " + zdt + " format = " + zdt.format(dateTimeFormatter));
                System.out.printf(out);
                // 输出format字符串仍然是localDateTime，说明localDateTime不带时区
//                zdt = 2022-08-05T09:04:24.283+10:30[Australia/LHI] format = 2022-08-05 09:04:24
//                Australia/LHI     +10:30
                Instant instant = zdt.toInstant();
                System.out.println("instant = " + instant.toString());
                // 输出的instant为带时区的瞬时，带时区的时间
//                zdt = 2022-08-05T09:08:05.312+12:45[Pacific/Chatham] format = 2022-08-05 09:08:05
//                Pacific/Chatham     +12:45
//                instant = 2022-08-04T20:23:05.312Z
            }
        }

        // 1.local to Utc
        String localTimeStr = "2022-08-05 09:21:01";
        String utcTimeStr = convertLocalToUtcTimeStr(localTimeStr);
        System.out.println("localTimeStr = " + localTimeStr + " convertLocalToUtcTimeStr then result = " + utcTimeStr);
        // localTimeStr = 2022-08-05 09:21:01 convertLocalToUtcTimeStr then result = 2022-08-05T01:21:01Z

        // 2.Utc to local
        String localTimeStr2 = convertUtcToLocalTimeStr(utcTimeStr);
        System.out.println("utcTimeStr = " + utcTimeStr + " convertUtcToLocalTimeStr then result = " + localTimeStr2);
        // utcTimeStr = 2022-08-05T01:21:01Z convertUtcToLocalTimeStr then result = 2022-08-05T09:21:01Z
    }

    public static String convertLocalToUtcTimeStr(String timeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt = LocalDateTime.parse(timeStr, dateTimeFormatter);

        ZoneId zone = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt = dt.atZone(zone);

        Instant instant = zdt.toInstant();
        System.out.println("instant = " + instant.toString());

        return instant.toString();
    }

    // Utc时间转为东八区时间 "Asia/Shanghai"
    public static String convertUtcToLocalTimeStr(String timeStr) {
        Instant instant = Instant.parse(timeStr);
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt = instant.atZone(zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return zdt.format(dateTimeFormatter);
    }
}
