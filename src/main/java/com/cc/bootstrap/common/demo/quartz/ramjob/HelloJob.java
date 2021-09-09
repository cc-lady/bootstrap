package com.cc.bootstrap.common.demo.quartz.ramjob;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 调度所需完成的任务 - 示例代码
 *   每次当scheduler执行job时，在调用其execute(…)方法之前会创建该类的一个新的实例；
 * 执行完毕，对该实例的引用就被丢弃了，实例会被垃圾回收；
 * 这种执行策略带来的一个后果是，job必须有一个无参的构造函数（当使用默认的JobFactory时）；
 * 另一个后果是，在job类中，不应该定义有状态的数据属性，因为在job的多次执行中，这些属性的值不会保留。
 */
@DisallowConcurrentExecution//同一时刻仅允许执行一个“SalesReportForJoe”实例，但可以并发地执行“SalesReportForMike”类的一个实例。
@PersistJobDataAfterExecution//告诉Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据。
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
