package com.cc.bootstrap.common.config.datasource;

import com.cc.bootstrap.common.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 切换数据源
 * @author: ChenChen
 * @date: 2022/4/19 16:08
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEST_HOLDER = new ThreadLocal<>();
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static final String MASTER_DS = "master";
    public static final String SLAVE_DS = "slave";

    /**
     * @Description 切换数据源
     * @param dataSource
     * @author ChenChen
     * @return void
     * @date 2023/1/12 15:43
     */
    public static synchronized void setDataSource(String dataSource) {
        try {
            if (DataSourceConfig.DATASOURCEMAP.containsKey(dataSource)) {
                CONTEST_HOLDER.set(dataSource);
            } else {
                throw new Exception("数据源" + dataSource + "不存在。");
            }
        }catch (Exception e) {
            LOGGER.error("数据源[{}]不存在，请检查配置。", dataSource, e);
        }
    }

    /**
     * @Description 获取数据源
     *
     * @author ChenChen
     * @return java.lang.String
     * @date 2023/1/12 15:43
     */
    public static String getDataSource() {
        return CONTEST_HOLDER.get();
    }

    /**
     * @Description 清理数据源
     *
     * @author ChenChen
     * @return void
     * @date 2023/1/12 15:43
     */
    public static void clearDataSource() {
        CONTEST_HOLDER.remove();
    }
}

