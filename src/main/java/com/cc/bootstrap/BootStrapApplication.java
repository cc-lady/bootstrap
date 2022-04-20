package com.cc.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: BootStrapApplication
 * @Description: 应用入口类
 * @author: CC
 * @date: 2021年4月23日 上午10:11:01
 */
@EnableAsync
@EnableDiscoveryClient//开启服务发现支持
@EnableFeignClients//开启feign调用支持
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//移除默认数据库配置类
public class BootStrapApplication extends SpringBootServletInitializer{

	//方便未受spring管理的普通类获取上下文中的Service
	private static ApplicationContext ctx;

	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	/* war部署：
	 * 如果要使用tomcat来加载jsp的话就必须继承springbootServletInitializer类重写其中的configure方法
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootStrapApplication.class);
    }

	public static void main(String[] args) {
		ctx = SpringApplication.run(BootStrapApplication.class, args);//启动引导引用程序
	}
}
