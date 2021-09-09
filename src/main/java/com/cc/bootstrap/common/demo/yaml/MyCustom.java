/**
 * MyCustom.java
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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ClassName:MyCustom
 * Function: 配置文件对应实体类
 *
 * @author   chenc
 * @version  
 * @since    Ver 1.1
 * @Date	 2021年6月24日		下午3:55:51
 *
 * @see 	 
 * @deprecated 
 */
public class MyCustom {
	
	private String nameSpace;//和yaml中属性名一致即可。
	private String applicationName;
	
	@JsonIgnore//对于类属性中不定义的那些属性，可以统一放在一个map中，方便解析和获取
	private Map<String, Object> additionProperties = new HashMap<>();

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	//通过注解放入Map中
	@JsonAnyGetter
	public Map<String, Object> getAdditionProperties(){
		return additionProperties;
	}
	
	@JsonAnySetter
	public void setAdditionProperties(String name, Object value) {
		this.additionProperties.put(name, value);
	}

}

