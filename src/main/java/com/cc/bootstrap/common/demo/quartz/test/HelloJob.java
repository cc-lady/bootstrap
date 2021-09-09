package com.cc.bootstrap.common.demo.quartz.test;

import java.util.Date;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.cc.bootstrap.BootStrapApplication;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.dao.UserMapper;

/**
 * 调度所需完成的任务 - 示例代码
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HelloJob implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    String jobSays;
    float myFloatValue;

    /* 简单打印当前时间
     */
	@Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());

        // 在job的执行过程中，可以从JobDataMap中取出数据
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        //可以通过注入获取，注意是从detail中注入到job实例，需要有setter
//        String jobSays = dataMap.getString("jobSays");
//        float myFloatValue = dataMap.getFloat("myFloatValue");
        User user = new User();
        user.setUserName("xxx"+ (new Random().nextInt()));
        user.setPassword("1323");//测试是否支持事务
        user.setEmail(user.getUserName() + "@111.com");

        //从jobDetail传递过来，支持事务
//        UserService userService = (UserService) dataMap.get("userService");
        UserMapper userMapper = (UserMapper) dataMap.get("userMapper");
        TestQuartzService testQuartzService = (TestQuartzService) dataMap.get("testQuartzService");
        try {
//            userMapper.insert(user);
//            user.setPassword(null);//测试是否支持事务
//            userService.saveUser2(user);
            testQuartzService.saveUsers(user);
        }catch (Exception e){
            LOGGER.debug("quartz定时调度时，新增失败" , e);
            e.printStackTrace();
        }

        //单独从Spring容器中获取Mapper，不支持事务
        user = new User("dd1","12","1234@234.com");
        //testQuartzService.saveUsers2(user);

        //单独从Spring容器中获取Mapper，不支持事务
//        UserService userService = BootStrapApplication.getCtx().getBean("userService", UserService.class);
        SqlSessionFactory sqlSessionFactory = BootStrapApplication.getCtx().getBean("sqlSessionFactory", SqlSessionFactory.class);
        SqlSession sqlSession =  sqlSessionFactory.openSession(false);
        try {
            userMapper = sqlSession.getMapper(UserMapper.class);
//            userService.saveUser(user);
            userMapper.insert(user);
            User user1 = new User();
            user1.setUserName("xxx"+ (new Random().nextInt()));
            user1.setEmail(user.getUserName() + "@111.com");
            user1.setPassword(null);//测试是否支持事务
            userMapper.insert(user1);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            LOGGER.debug("quartz定时调度时，新增失败" , e);
            e.printStackTrace();
        }

        //使用Spring的手動控制事務：TransactionTemplate
        TransactionTemplate transactionTemplate = BootStrapApplication.getCtx().getBean("transactionTemplate", TransactionTemplate.class);
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                UserMapper userMapper = (UserMapper) dataMap.get("userMapper");

                User user = new User("dd1","12","1234@234.com");
                userMapper.insert(user);
                User user2 = new User("dd1",null,"1234@234.com");
                userMapper.insert(user2);

                return "success";
            }
        });

        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);//单个实例单次运行，虽然调度打印十次，但每次获取属性相同，可联想到jobdetail启动运行时间
    }
}
