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
        LOGGER.info("当前数据源1：[{}]", DataSourceContextHolder.getDataSource());
        LOGGER.info("切换数据源：");
        // 注意切换所在的方法及父方法等都不能使用事务注解，否则会切换不成功。
        DataSourceContextHolder.setDataSource(DataSourceContextHolder.SLAVE_DS);//切换数据源
        LOGGER.info("当前数据源2：[{}]", DataSourceContextHolder.getDataSource());
        try {
            for (int i = 0; i < userList.size(); i++) {
                userService.insert(i, userList.get(i));
            }
        } catch (Exception e) {
            LOGGER.info("操作失败");
        } finally {
            // 无论成功失败与否，都要记得清除数据源，否则会影响其他接口。
            DataSourceContextHolder.clearDataSource();
        }
    }
}
