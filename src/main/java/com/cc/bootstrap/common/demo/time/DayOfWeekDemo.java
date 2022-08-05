package com.cc.bootstrap.common.demo.time;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @Description: DayOfWeek 一周里面第几天，即星期几
 * @author: ChenChen
 * @date: 2022/8/4 17:17
 */
public class DayOfWeekDemo {
    public static void main(String[] args) {
        DayOfWeek dow = DayOfWeek.MONDAY;
        Locale locale = Locale.getDefault();
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));//星期一
        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));//一
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));//星期一
    }
}
