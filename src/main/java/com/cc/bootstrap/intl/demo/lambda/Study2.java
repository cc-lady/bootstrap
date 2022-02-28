package com.cc.bootstrap.intl.demo.lambda;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName Exercise
 * @Description 第二章练习题
 * @createTime 2021年12月08日 20:34:00
 */
public class Study2 {

    public static void main(String[] args) {
        /**
         * ThreadLocal Lambda 表达式。Java 有一个 ThreadLocal 类，作为容器保存了当前线程里 局部变量的值。
         * Java 8 为该类新加了一个工厂方法，接受一个 Lambda 表达式，并产生 一个新的 ThreadLocal 对象，而不用使用继承，语法上更加简洁
         * DateFormatter 类是非线程安全的。使用构造函数创建一个线程安全的 DateFormatter 对象，并输出日期，如“01-Jan-1970”。
         */
//        Supplier<DateFormatter> newDateFormat = () -> {
//            return new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy", Locale.UK));
//        };
        // 使用线程安全的日期格式化
//        ThreadLocal<DateFormatter> threadLocal = ThreadLocal.withInitial(newDateFormat);
        ThreadLocal<DateFormatter> threadLocal = ThreadLocal.withInitial(() -> {//行为也可以匿名传递，代码更简洁
            return new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy", Locale.UK));
        });
//        Calendar calendar = new GregorianCalendar(1970,0,1);//月份减1，可以用属性更方便
        Calendar calendar = new GregorianCalendar(1970,Calendar.JANUARY,1);
        String date = threadLocal.get().getFormat().format(calendar.getTime());//new Date(1970-1900,0,1) 已过时
        System.out.println(date);
    }
}
