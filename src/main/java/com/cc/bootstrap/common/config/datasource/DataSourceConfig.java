package com.cc.bootstrap.common.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.BeanFactoryDataSourceLookup;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据源配置
 * @author: ChenChen
 * @date: 2022/4/19 16:09
 */
@MapperScan(basePackages = {"com.cc.bootstrap.page.dao","com.cc.bootstrap.intl.dao"},
        sqlSessionFactoryRef = "sqlSessionFactory-oc")
@Configuration
public class DataSourceConfig implements ApplicationContextAware {

    private ApplicationContext context;

    /**
     * 数据源1
     * spring.datasource.dev：application.properteis中对应属性的前缀
     * @return
     */
    @Profile({"dev", "prod"})
    @Bean("master")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源2
     * spring.datasource.test：application.properteis中对应属性的前缀
     * @return
     */
    @Profile({"dev", "prod"})
    @Bean("slave")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamic")
    public DataSource dataSource(@Qualifier("master") DataSource dataSourceMaster,
                                 @Qualifier("slave") DataSource dataSourceSlave) {
        ApplicationContext c = getContext();

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("master", dataSourceMaster);
        dsMap.put("slave",dataSourceSlave);

        dynamicDataSource.setTargetDataSources(dsMap);
        dynamicDataSource.setDataSourceLookup(new BeanFactoryDataSourceLookup(c.getAutowireCapableBeanFactory()));
        return dynamicDataSource;
    }

    public ApplicationContext getContext() {
        return context;
    }

    /**
     * 配置@Transactional注解
     * @return
     */
    @Primary
    @Bean(name = "transactionManager-dynamic")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamic") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



    @Primary
    @Bean(name = {"sqlSessionFactory-oc", "th-default-ssf"})
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamic") DataSource dataSource) throws Exception {
        // MP全局配置
        GlobalConfig gcfg = new GlobalConfig();
        // 设置数据源类型
        MybatisConfiguration mcfg = new MybatisConfiguration();
        mcfg.setJdbcTypeForNull(JdbcType.NULL);
        // 使用MP提供的SSF，配套MP的分页插件，分页支持ORACLE，不支持Sybase
        final MybatisSqlSessionFactoryBean ssfb = new MybatisSqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        ssfb.setGlobalConfig(gcfg);
        ssfb.setConfiguration(mcfg);

        return ssfb.getObject();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}

