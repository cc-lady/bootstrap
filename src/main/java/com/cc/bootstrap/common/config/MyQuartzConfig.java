package com.cc.bootstrap.common.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * 任务调度quartz调度配置类
 */
@Configuration
public class MyQuartzConfig {

    /**
     * 初始注入scheduler
     * @return
     * @throws SchedulerException
     */
	//无需配置，默认可以获得
//    @Bean(name = "myScheduler")  
//    public Scheduler scheduler() throws SchedulerException{
//        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        scheduler.start();
//        return scheduler;
//    }
    
    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        ScheduledExecutorService ss= Executors.newScheduledThreadPool(5);
        return ss;

    }
    @Bean
    public TaskScheduler taskScheduler(){

        return new  ConcurrentTaskScheduler();

    }
}
