package com.cc.bootstrap.common.demo.quartz.springcloud;

import java.util.Date;

import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.service.UserService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 调度所需完成的任务 - 示例代码
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HelloJob extends QuartzJobBean {//使用Springboot quartz

    String jobSays;
    float myFloatValue;

    //使用Springboot quartz，可以在Job中注入Spring的Bean
    @Autowired
    private UserService userService;

    @Autowired
    private TestQuartzService1 testQuartzService1;

    /* 使用Spring的Bean，新增一个User
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());

        // 在job的执行过程中，可以从JobDataMap中取出数据
        JobKey key = jobExecutionContext.getJobDetail().getKey();

//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        //可以通过注入获取，注意是从detail中注入到job实例，需要有setter
//        String jobSays = dataMap.getString("jobSays");
//        float myFloatValue = dataMap.getFloat("myFloatValue");

        userService.saveUser(new User("ee","23","23@23.com"));
        testQuartzService1.saveUsers(new User("ee","23","23@23.com"));
        //testQuartzService1.saveUsers2(new User("ee","23","23@23.com"));
        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);//单个实例单次运行，虽然调度打印十次，但每次获取属性相同，可联想到jobdetail启动运行时间
    }
}
