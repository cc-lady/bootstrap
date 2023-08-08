package com.cc.bootstrap.common.config;

import com.cc.bootstrap.common.vo.DbManagerVO;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.io.ClasspathLocationStrategy;
import org.apache.commons.configuration2.io.CombinedLocationStrategy;
import org.apache.commons.configuration2.io.FileLocationStrategy;
import org.apache.commons.configuration2.io.FileSystemLocationStrategy;
import org.apache.commons.configuration2.io.ProvidedURLLocationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Description: 主要为了演示获取properties配置文件值
 * @author: ChenChen
 * @date: 2023/8/4 16:14
 */
@Component
public class GbaseDbManagerConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(GbaseDbManagerConfiguration.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${api.gbase.dbmanager}")
    private String dbManagerConfig;

    /**
     * @Description 获取gbase分行数据库管理员信息
     *
     * @author ChenChen
     * @return java.util.Map<java.lang.String, com.cc.bootstrap.common.vo.DbManagerVO>
     * @date 2023/8/4 16:25
     */
    @Bean(name = "dbManagerConfigs")
    public Map<String, DbManagerVO> getDbManagerConfigs() {
        Map<String, DbManagerVO> dbManagerConfigs = new HashMap<>();

        try {
            //1.使用spring的PropertiesLoaderUtils.loadProperties
            InputStream inputStream = resourceLoader.getResource(dbManagerConfig).getInputStream();
            Properties properties = PropertiesLoaderUtils.loadProperties(
                    new EncodedResource(
                            new InputStreamResource(inputStream), StandardCharsets.UTF_8));
            if(null != properties) {
                Set<String> keys = properties.stringPropertyNames();
                for (String key : keys) {
                    String[] values = properties.getProperty(key).split(",");
                    DbManagerVO dbManagerVO = new DbManagerVO();
                    dbManagerVO.setDepartment(key);
                    dbManagerVO.setDbManagerUser(values[0]);
                    dbManagerVO.setDepartmentName(values[1]);
                    dbManagerConfigs.put(key, dbManagerVO);
                }
            }
            //2.使用apache commons configuration2
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<PropertiesConfiguration> builder = null;
            // 和原使用Properties类似，通过resourceLoader获取File
//                    builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
//                            PropertiesConfiguration.class)
//                            .configure(params.fileBased()
//                                    .setFile(resourceLoader.getResource(dbManagerConfig).getFile())
//                                    .setEncoding("UTF-8")
//                                    .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
//                                    .setThrowExceptionOnMissing(true));
            // 文件定位策略：传递FileName即可，文件定位策略会帮你查找
            List<FileLocationStrategy> subs = Arrays.asList(new ProvidedURLLocationStrategy(),
                    new FileSystemLocationStrategy(), new ClasspathLocationStrategy());
            FileLocationStrategy strategy = new CombinedLocationStrategy(subs);
            builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
                    PropertiesConfiguration.class)
                    .configure(params.fileBased()
                            .setFileName(dbManagerConfig)//使用文件定位策略查找文件
                            .setEncoding("UTF-8")//编码，防止默认编码导致的中文乱码问题
                            .setListDelimiterHandler(new DefaultListDelimiterHandler(','))//属性分隔符
                            .setThrowExceptionOnMissing(true)
                            .setLocationStrategy(strategy));//设置文件定位策略
            PropertiesConfiguration config = builder.getConfiguration();
            for (Iterator<String> it = config.getKeys(); it.hasNext(); ) {
                String key = it.next();
                String[] values = config.getStringArray(key);
                DbManagerVO dbManagerVO = new DbManagerVO();
                dbManagerVO.setDepartment(key);
                dbManagerVO.setDbManagerUser(values[0]);
                dbManagerVO.setDepartmentName(values[1]);
                dbManagerConfigs.put(key, dbManagerVO);
            }
        } catch (Exception e) {
            LOGGER.error("读取gbase分行数据库管理员信息配置异常！", e);
        }

        return dbManagerConfigs;
    }
}
