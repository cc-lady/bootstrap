package com.cc.bootstrap.common.demo.test;

import com.cc.bootstrap.common.config.datasource.DataSourceContextHolder;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: DataSourceChangeControlService
 * @author: ChenChen
 * @date: 2022/4/19 17:12
 */
@Component
public class DataSourceChangeControlService {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceChangeControlService.class);

    @Autowired
    private UserService userService;

    /**
     * @Description testChange
     * @param userList
     * @author ChenChen
     * @return void
     * @date 2022/4/19 17:14
     */
    public void testChange(List<User> userList) {
        LOGGER.info("当前数据源1：[{}]", DataSourceContextHolder.getDB());
        LOGGER.info("切换数据源：");
        DataSourceContextHolder.setDB("slave");//切换数据源
        LOGGER.info("当前数据源2：[{}]", DataSourceContextHolder.getDB());
        for(int i = 0; i < userList.size(); i++) {
            userService.insert(i, userList.get(i));
        }
    }
}
