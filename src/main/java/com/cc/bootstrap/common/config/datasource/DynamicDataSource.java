package com.cc.bootstrap.common.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * @Description: 动态数据源
 * @author: ChenChen
 * @date: 2022/4/19 16:09
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String ds = DataSourceContextHolder.getDataSource();
        if(StringUtils.isEmpty(ds)) {
            ds = DataSourceContextHolder.MASTER_DS;
        }
        return ds;
    }
}

