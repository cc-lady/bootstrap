/**
 * StringTest.java
 * com.cc.bootstrap.common.demo.test
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2021年6月29日 		chenc
 *
 * Copyright (c) 2021, TNT All Rights Reserved.
*/

package com.cc.bootstrap.common.demo.test;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:StringTest
 * Function: 字符串方法测试
 * @author   chenc
 * @version  
 * @since    Ver 1.1
 * @Date	 2021年6月29日		上午11:12:44
 */
public class StringTest {

	private static Logger LOGGER = LoggerFactory.getLogger(StringTest.class);
	
	/**
	 * @method subStrOffLastComma(截取最后一个字符前的字符串)
	 * @return String
	 * @author ChenChen
	 * @date 2021年6月29日 上午11:13:04
	 */
	public static String subStrOffLast(String paramStr) throws RuntimeException {
		LOGGER.info("the method subStrOffLastComma param : paramStr = {}" , paramStr);
		// 非法参数直接抛出异常：参数为null或空字符串
		if(StringUtils.isEmpty(paramStr)) {
			throw new RuntimeException("the param is not valid!");
		}
		// 截取字符串
		String resultStr = paramStr.substring(0, paramStr.length()-1);
		LOGGER.info("the method subStrOffLastComma result : resultStr = {}" , resultStr);
		return resultStr;
	}
	
	public static void main(String[] args) {
		String paramStr = "aa";
		try {
			StringTest.subStrOffLast(paramStr);
		} catch (RuntimeException e) {
			LOGGER.error("the method subStrOffLastComma error : {}", e.getMessage(), e);
		}
	}

}

