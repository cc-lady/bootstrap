/**
 * MyCustomConfigurationTest.java
 * com.cc.bootstrap.demo
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2021年6月24日 		chenc
 *
 * Copyright (c) 2021, TNT All Rights Reserved.
*/

package com.cc.bootstrap.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cc.bootstrap.BootStrapApplication;
import com.cc.bootstrap.common.demo.yaml.MyCustom;
import com.cc.bootstrap.common.demo.yaml.MyCustomConfiguration;
import com.cc.bootstrap.page.user.TestUser;

/**
 * ClassName:MyCustomConfigurationTest
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   chenc
 * @version  
 * @since    Ver 1.1
 * @Date	 2021年6月24日		下午4:22:30
 *
 * @see 	 
 * @deprecated 
 */
@RunWith(SpringJUnit4ClassRunner.class)//引入spring对JUnit4的支持
//@SpringApplicationConfiguration(classes = SpringbootDemoApplication.class) springboot1.4后被替换使用以下
@SpringBootTest(classes = BootStrapApplication.class)//指定springboot的启动类
@WebAppConfiguration//开启web应用的配置，用于模拟ServletContext
@Transactional//增删改自动回滚数据，不体现在数据库中
public class MyCustomConfigurationTest {
	private static Logger LOGGER = LoggerFactory.getLogger(MyCustomConfigurationTest.class);

	@Autowired
	private MyCustomConfiguration myCustomConfiguration;
	
	@Test
	public void testGetMyCustom() throws Exception{
		MyCustom myCustom = myCustomConfiguration.getMyCustom();
		LOGGER.info("myCustom : applicationName = " + myCustom.getApplicationName());
	}
}

