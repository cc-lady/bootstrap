package com.cc.bootstrap.common.demo.quartz.simplettrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz调度任务示例 ---- 简单触发器
 * 此示例代码主要说明Simple Trigger 简单触发器的使用
 * SimpleTrigger可以满足的调度需求是：在具体的时间点执行一次，或者在具体的时间点执行，并且以指定的间隔重复执行若干次。
 * SimpleTrigger的属性包括：开始时间、结束时间、重复次数以及重复的间隔。
 * 重复次数，可以是0、正整数，以及常量SimpleTrigger.REPEAT_INDEFINITELY。
 * 重复的间隔，必须是0，或者long型的正数，表示毫秒。注意，如果重复间隔为0，trigger将会以重复次数并发执行(或者以scheduler可以处理的近似并发数)。
 * TriggerBuilder(以及Quartz的其它builder)会为那些没有被显式设置的属性选择合理的默认值。比如：如果你没有调用withIdentity(..)方法，---建议自己手动赋值
 * TriggerBuilder会为trigger生成一个随机的名称；如果没有调用startAt(..)方法，则默认使用当前时间，即trigger立即生效。
 */
public class QuartzTest {

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("myFloatValue", new Random().nextFloat())
                    .build();

            // 触发器开始时间
//            Date startDate = null;
//            Calendar calendar = new GregorianCalendar();
//            calendar.set(2021,4,21,15,31,0);
//            startDate = calendar.getTime();
//            System.out.println(startDate.toString());

            // Trigger the job to run
            // (1)指定时间开始触发，不重复：-- 测试结果：只执行1次
//            SimpleTrigger trigger = (SimpleTrigger) newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .startAt(startDate)                                       // some Date
//                    .forJob("job1", "group1")                 // identify job with name, group strings
//                    .build();

            // (2)指定时间触发，每隔10秒执行一次，重复10次：-- 测试结果：执行11次
//            SimpleTrigger trigger = newTrigger()
//                    .withIdentity("trigger3", "group1")
//                    .startAt(new Date())  // if a start time is not given (if this line were omitted), "now" is implied
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(10)
//                            .withRepeatCount(10)) // note that 10 repeats will give a total of 11 firings
//                    .forJob(job) // identify job with handle to its JobDetail itself
//                    .build();

            // (3)1分钟以后开始触发，仅执行一次：
//            SimpleTrigger trigger = (SimpleTrigger) newTrigger()
//                    .withIdentity("trigger5", "group1")
//                    .startAt(futureDate(1, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
//                    .forJob(job) // identify job with its JobKey
//                    .build();

            // (4)立即触发，每个1分钟执行一次，直到22:00：
//            SimpleTrigger trigger = newTrigger()
//                    .withIdentity("trigger7", "group1")
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInMinutes(1)
//                            .repeatForever())
//                    .endAt(dateOf(22, 0, 0))
//                    .build();

            // (5)将在下一个小时的整点触发，然后每10秒重复一次：
            SimpleTrigger trigger = newTrigger()
                    .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
                    .startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    // note that in this example, 'forJob(..)' is not called which is valid
                    // if the trigger is passed to the scheduler along with the job
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // run it for a while
            Thread.sleep(110000000);

            // shutdown
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }

    // 返回下一个整小时
    private static Date evenHourDate(Object o) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        if(calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0){
            return date;
        }else{
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)+1);
            return calendar.getTime();
        }
    }
}