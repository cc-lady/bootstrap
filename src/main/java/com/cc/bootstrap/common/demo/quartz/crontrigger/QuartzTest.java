package com.cc.bootstrap.common.demo.quartz.crontrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Random;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz调度任务示例 ---- 定时触发器
 * CronTrigger通常比Simple Trigger更有用，如果您需要基于日历的概念而不是按照SimpleTrigger的精确指定间隔进行重新启动的作业启动计划。
 * 使用CronTrigger，您可以指定号时间表，例如“每周五中午”或“每个工作日和上午9:30”，甚至“每周一至周五上午9:00至10点之间每5分钟”和1月份的星期五“。
 * 即使如此，和SimpleTrigger一样，CronTrigger有一个startTime，它指定何时生效，以及一个（可选的）endTime，用于指定何时停止计划。
 * Cron Expressions
 * Cron-Expressions用于配置CronTrigger的实例。Cron Expressions是由七个子表达式组成的字符串，用于描述日程表的各个细节。这些子表达式用空格分隔，并表示：
 * Seconds Minutes Hours Day-of-Month Month Day-of-Week Year (optional field)
 * 还有一些特殊符号，例如通配符，L，W等 您可以在JavaDoc中找到更多的org.quartz.CronExpression
 *
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
            // (1)每隔2分钟，每天上午8点至下午5点之间：
            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                    .forJob("job1", "group1")
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
}