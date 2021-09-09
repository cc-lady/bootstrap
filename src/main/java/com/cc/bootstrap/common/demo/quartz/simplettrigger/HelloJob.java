package com.cc.bootstrap.common.demo.quartz.simplettrigger;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 调度所需完成的任务 - 示例代码
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HelloJob implements Job {

    String jobSays;
    float myFloatValue;

    /* 简单打印当前时间
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());

        // 在job的执行过程中，可以从JobDataMap中取出数据
        JobKey key = jobExecutionContext.getJobDetail().getKey();

//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        //可以通过注入获取，注意是从detail中注入到job实例，需要有setter
//        String jobSays = dataMap.getString("jobSays");
//        float myFloatValue = dataMap.getFloat("myFloatValue");

        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);//单个实例单次运行，虽然调度打印十次，但每次获取属性相同，可联想到jobdetail启动运行时间
    }
}
