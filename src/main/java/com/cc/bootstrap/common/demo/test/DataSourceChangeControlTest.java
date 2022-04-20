package com.cc.bootstrap.common.demo.test;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.schema.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数据源切换测试
 * @author: ChenChen
 * @date: 2022/4/19 16:14
 */
@RestController
@RequestMapping(value = "/test/datasource")
public class DataSourceChangeControlTest {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceChangeControlTest.class);

    @Autowired
    private AsyncThreadPoolService asyncThreadPoolService;

    @Autowired
    private DataSourceChangeControlService dataSourceChangeControlService;


    @GetMapping(value = "/thread/change")
    public ResponseResult threadChange() {
        List<User> userList = new ArrayList<>();
        User user = null;
        for (int i = 0; i < 100; i++) {
            user = new User();
            user.setUserName("master");
            user.setEmail("123@qq.com");
            user.setPassword("123456");
            user.setAddress("beijing"+ i);
            user.setRole(1);
            user.setMobilePhone("188" + i);
            user.setNote("testuser" +i);
            userList.add(user);
        }
        dataSourceChangeControlService.testChange(userList);
        return ResponseResult.success();
    }
}
