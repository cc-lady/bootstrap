package com.cc.bootstrap.common.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @Description: 配置文件解密原理
 * @author: ChenChen
 * @date: 2023/1/12 11:31
 */
public class JasyptUtil {
    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("G3CvKz6pLd9");
        String encrypted = encryptor.encrypt("Cc123456#");
        //加密后的数据：每次运行不同，但是都可被解密成功还原密码
        System.out.println(encrypted);
        String decrypt = encryptor.decrypt(encrypted);
        //解密后的数据：testuser
        System.out.println(decrypt);

        //springboot集成使用
        //1.使用 @SpringBootApplication 或 @EnableAutoConfiguration（@SpringBootApplication包含此注解） 将在整个 Spring 环境中启用可加密属性，
        // 只需将 starter jar jasypt-spring-boot-starter 添加到您的类路径中
        //2.设置秘钥：jasypt.encryptor.password= xxx
        //3.将数据源密码改为ENC(加密后数据源密码)格式后，启动时可自动解析获取真实密码
    }
}
