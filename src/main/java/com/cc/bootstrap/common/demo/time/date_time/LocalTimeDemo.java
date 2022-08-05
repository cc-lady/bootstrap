package com.cc.bootstrap.common.demo.time.date_time;

import java.time.LocalTime;

/**
 * @Description: LocalTime 类
 * 类似于名称以Local为前缀的其他类，但只处理时间。
 * 此类对于表示一天中基于人类的时间很有用，例如电影时间或本地图书馆的开放和关闭时间。它还可用于创建数字时钟
 * LocalTime类不存储时区或夏令时信息。
 * @author: ChenChen
 * @date: 2022/8/4 17:34
 */
public class LocalTimeDemo {
    public static void main(String[] args) throws InterruptedException {
        LocalTime thisSec;

        for (;;) {
            thisSec = LocalTime.now();

            // implementation of display code is left to the reader
            display(thisSec.getHour(), thisSec.getMinute(), thisSec.getSecond());
            Thread.sleep(1000);
        }
    }

    private static void display(int hour, int minute, int second) {
        System.out.println("hour [" + hour + "] minute [" + minute + "] second [" + second + "]");
    }
}
