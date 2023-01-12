package com.cc.bootstrap.common.config.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

/**
 * @Description: gbase使用mysql方言，mybatis-plus不支持gbase方言
 * @author: ChenChen
 * @date: 2023/1/12 15:30
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class GbaseDbTypeIntercepter extends PaginationInterceptor implements Interceptor {
    final String GBASE = "gbase";
    final String MYSQL = "mysql";

    /**
     * @Description 获取数据库类型
     * @param url
     * @author ChenChen
     * @return java.lang.String
     * @date 2023/1/12 15:33
     */
    public String getDbType(String url) {
        boolean startFlag = false;
        StringBuilder dbType = new StringBuilder();
        char[] chars = url.toCharArray();
        for (char c: chars) {
            if(startFlag && c < 65) {
                break;
            }
            if(startFlag) {
               dbType.append(c);
            }
            if(c < 65) {
                startFlag = true;
            }
        }
        return dbType.toString();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Connection connection = (Connection) invocation.getArgs()[0];
        String url = connection.getMetaData().getURL();
        String dbType = getDbType(url);
        if(GBASE.equals(dbType)) {
            dbType = MYSQL;
        }
        this.setDialectType(dbType);
        return super.intercept(invocation);
    }
}
