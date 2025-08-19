package com.cc.bootstrap.common.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: SqlSession配置
 * @author: ChenChen
 * @date: 2025-08-19 15:53
 */
@Configuration
public class SqlSessionConfig {


    /**
     * @Description 批量SqlSessionTemplate
     * @param sqlSessionFactory
     * @author ChenChen
     * @return org.mybatis.spring.SqlSessionTemplate
     * @date 2025-08-19 15:56
     */
    @Bean(name = "batchSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }
}
