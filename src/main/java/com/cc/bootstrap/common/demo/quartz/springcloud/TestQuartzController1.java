package com.cc.bootstrap.common.demo.quartz.springcloud;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Random;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.bootstrap.common.demo.quartz.test.HelloJob;
import com.cc.bootstrap.common.demo.quartz.test.TestQuartzService;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.dao.UserMapper;
import com.cc.bootstrap.page.service.UserService;

/**
 * quartz调度任务示例 ---- 正式代码使用
 * 服务关闭下次重启时调度就不存在了，应该如何解决？
 * 思路：放入数据库中，下次重启时读取数据库中任务状态，将需要重新调度的拉取，后台自动调度。
 * 如果这样做了，集群中只需要有一台机器在调度，该如何解决：
 * ---刷新自己缓存的调度就可以每台机器都做，假设任务是那种只能集群中其中一台机器调用，该如何解决？
 * 思路：放置标志位，例如1表示调度中，即拉取或执行时都判断此状态位，再进行拉取或执行。
 */
@Controller//不用RestController因为可能会跳转页面
@RequestMapping(value="/quartz-test1")
public class TestQuartzController1 {
    private static Logger LOGGER = LoggerFactory.getLogger(TestQuartzController1.class);

    @Autowired
//    @Qualifier(value = "myScheduler")
    private Scheduler Scheduler;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestQuartzService testQuartzService;

    //1.项目刚构建完，测试mybatis连接查询 http://localhost:9011/quartz-test1/testQuartz
    @ResponseBody//不加会返回xml格式，还是json格式方便使用，尤其是前端调用的数据
    @RequestMapping(value = "/testQuartz", produces={"application/json; charset=UTF-8"})
    public String testQuartz() throws SchedulerException {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("userService", userService);
        jobDataMap.put("userMapper", userMapper);
        jobDataMap.put("testQuartzService", testQuartzService);

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", new Random().nextFloat())
                .usingJobData(jobDataMap)
                .build();

        // Trigger the job to run
        // 指定时间开始触发，不重复：-- 测试结果：只执行1次
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(new Date()) // some Date
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(100)
                        .repeatForever())
                .forJob("job1", "group1")                 // identify job with name, group strings
                .build();
        

        // Tell quartz to schedule the job using our trigger
        Scheduler.scheduleJob(job, trigger);
        LOGGER.debug("Scheduler run ......");
        return "后台已开始执行调度任务。。。。。。";

    }


    //1.測試事務 http://localhost:9011/quartz-test1/testQuartz2
    @ResponseBody//不加会返回xml格式，还是json格式方便使用，尤其是前端调用的数据
    @RequestMapping(value = "/testQuartz2", produces={"application/json; charset=UTF-8"})
    public String testQuartz2() throws SchedulerException {
        User user = new User("dd","12","1234@234.com");
        testQuartzService.saveUsers(user);
        return "success";
    }
}