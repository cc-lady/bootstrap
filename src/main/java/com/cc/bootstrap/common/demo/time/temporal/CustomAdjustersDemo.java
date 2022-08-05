package com.cc.bootstrap.common.demo.time.temporal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: 自定义调节器
 * 您还可以创建自己的自定义调节器。为此，您需要创建一个使用adjustInto(Temporal)方法实现TemporalAdjuster接口 的类。
 * @author: ChenChen
 * @date: 2022/8/5 11:34
 */
public class CustomAdjustersDemo implements TemporalAdjuster {

    /**
     * 示例中的 类 是自定义调整器。PaydayAdjuster评估传入的日期并返回下一个发薪日，假设发薪日每月发生两次：
     * 在 15 日，然后在每月的最后一天。如果计算的日期发生在周末，则使用上一个星期五。假定当前日历年。PaydayAdjusterNextPayday
     */
    public Temporal adjustInto(Temporal input) {
        LocalDate date = LocalDate.from(input);
        int day;
        if (date.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }
        date = date.withDayOfMonth(day);
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }

        return input.with(date);
    }

    public static void main(String[] args) {
        // 使用with方法以与 预定义的调整器相同的方式调用调整器。以下代码行来自NextPayday示例：
        LocalDate date = LocalDate.now();// 2022-08-05
        LocalDate nextPayday = date.with(new CustomAdjustersDemo());
        System.out.println(nextPayday);//当前的下一个发薪日 2022-08-15
    }
}
