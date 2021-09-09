package com.cc.bootstrap.common.demo.quartz.ramjob;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Random;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * quartz调度任务示例 ---- store in ram
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
                    .usingJobData("jobSays", "Hello World!")//那么如何给job实例增加属性或配置呢？如何在job的多次执行中，跟踪job的状态呢？答案就是:JobDataMap，JobDetail对象的一部分。
                    .usingJobData("myFloatValue", new Random().nextFloat())//在jobDetail运行实例时可以获取，具体见HelloJob.java
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            // 触发作业立即运行，然后每40秒重复一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // run it for a while
            Thread.sleep(40000);

            // shutdown
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}