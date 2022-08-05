package com.cc.bootstrap.common.demo.time.duration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @Description: 计时单元
 * ChronoUnit枚举，在 The Temporal Package 中讨论 ，定义了用于测量时间的单位。
 * 当您只想测量单个时间单位（例如天或秒）中的时间量时，ChronoUnit.between方法很有用。
 * @author: ChenChen
 * @date: 2022/8/5 15:18
 */
public class ChronoUnitDemo {
    public static void main(String[] args) {
        Instant previous = Instant.parse("2022-08-05T07:14:00Z");
        Instant current;
        current = Instant.now();
        if (previous != null) {
            long gap = ChronoUnit.SECONDS.between(previous,current);
            System.out.println(previous + " to " + current + " past :" + gap + "s");
        }
    }
}
