/**
 * MyCustomConfiguration.java
 * com.cc.bootstrap.common.demo.yaml
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2021年6月24日 		chenc
 *
 * Copyright (c) 2021, TNT All Rights Reserved.
*/

package com.cc.bootstrap.common.demo.yaml;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * ClassName:MyCustomConfiguration
 * Function: yaml配置文件解析为java类
 * Reason:	 yaml-->javabean 学习demo
 * Ps: 去单元测试下启动MyCustomConfigurationTest来查看结果
 * @author   chenc
 * @version  
 * @since    Ver 1.1
 * @Date	 2021年6月24日		下午4:01:26
 *
 * @see 	 
 */
@Component
public class MyCustomConfiguration implements InitializingBean{//初始化bean后执行一些操作
	
	private static Logger logger = LoggerFactory.getLogger(MyCustomConfiguration.class);
	
	private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
	
	private MyCustom myCustom;

	/**
	 * 初始化MyCustomConfiguration后执行此方法，初始化解析yaml文件，放入此对象的MyCustom属性中使用。
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream ins = new ClassPathResource("yamlconfig/mycustom.yaml").getInputStream();
		
		myCustom = objectMapper.readValue(ins, MyCustom.class);
		logger.info("myCustom: {} " + objectMapper.writeValueAsString(myCustom));
	}
	
	
	public MyCustom getMyCustom() {
		return myCustom;
	}

}

