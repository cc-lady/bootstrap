package com.cc.bootstrap.common.demo.quartz.test;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.dao.UserMapper;
import com.cc.bootstrap.page.service.UserService;

/**
 * 测试事务
 */
@Service
public class TestQuartzService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void saveUsers(User user){
        userMapper.insert(user);
        User user1 = new User();
        user1.setUserName("xxx"+ (new Random().nextInt()));
        user1.setEmail(user.getUserName() + "@111.com");
        userService.saveUser(user1);
    }

    //此事務不成功
    //TODO : 为什么
//    public void saveUsers2(User user){
//        //单独从Spring容器中获取userMapper，不支持事务
////        UserService userService = BootStrapApplication.getCtx().getBean("userService", UserService.class);
//        SqlSessionFactory sqlSessionFactory = BootStrapApplication.getCtx().getBean("sqlSessionFactory", SqlSessionFactory.class);
//        TransactionFactory transactionFactory = sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory();
//        transactionFactory = new JdbcTransactionFactory();
//        SqlSession sqlSession =  sqlSessionFactory.openSession(false);
//        try {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
////            userService.saveUser(user);
//            userMapper.insert(user);
//            User user1 = new User();
//            user1.setUserName("xxx"+ (new Random().nextInt()));
//            user1.setEmail(user.getUserName() + "@111.com");
//            user1.setPassword(null);//测试是否支持事务
//            userMapper.insert(user1);
//            sqlSession.commit();
//        }catch (Exception e){
//            sqlSession.rollback();
//            e.printStackTrace();
//        }
//    }

}
