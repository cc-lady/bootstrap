package com.cc.bootstrap.common.config.datasource;

/**
 * @Description: 切换数据源
 * @author: ChenChen
 * @date: 2022/4/19 16:08
 */
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "master";

    /**
     * 设置数据源名
     * @param dbType
     */
    public static void setDB(String dbType) {
        ThreadContext.getContext().setAttribute(DEFAULT_DS, dbType);
    }

    /**
     * 获取数据源名
     * @return
     */
    public static String getDB() {
        return (String) ThreadContext.getContext().getAttribute(DEFAULT_DS);
    }

    /**
     * 清除数据源名
     */
    public static void clearDB() {
        ThreadContext.getContext().removeAttribute(DEFAULT_DS);
    }
}

