package com.cc.bootstrap.intl.demo.encryptor;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Jasypt加密解密配置
 * @author: ChenChen
 * @date: 2025-08-04 8:36
 */
@Configuration
public class JasyptConfig {

    @Bean
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("aiml@swagger");
        config.setAlgorithm("PBEWithMd5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    /**
     * 1.使用jasypt加密
     * com.github.ulisesbocchio  jasypt-spring-boot-starter
     * org.springframework.cloud spring-cloud-context
     * 2.Jasypt加密解密配置
     * com.cc.bootstrap.intl.demo.encryptor.JasyptConfig
     * 3.yaml配置加密解密器
     * jasypt.encryptor.bean=encryptorBean
     * 4.配置文件将属性改为ENC()格式
     * swagger.production=false
     * swagger.basic.enable=true
     * swagger.basic.username=aiml
     * swagger.basic.password=ENC(XXX)
     * 5.测试类生成密码
     * com.cc.bootstrap.demo.JasyptConfigTest
     */


}
