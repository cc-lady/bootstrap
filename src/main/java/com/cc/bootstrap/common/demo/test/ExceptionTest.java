/**
 * ExceptionTest.java
 * com.cc.bootstrap.common.demo.test
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2021年6月25日 		chenc
 *
 * Copyright (c) 2021, TNT All Rights Reserved.
*/

package com.cc.bootstrap.common.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:ExceptionTest
 * Function: 异常捕获情况测试
 * Reason:	 测试Exception参数带上e抛出和不带e结果有什么不同 --- 测试结果：带上e，看到报错真正原因！
 * @author   chenc
 * @version  
 * @since    Ver 1.1
 * @Date	 2021年6月25日		下午2:16:48
 *
 * @see
 */
public class ExceptionTest {
	private static Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);

	
	public void exceptionThrow () throws Exception {
		try {
			int i = 1/0;
		} catch (Exception e) {
			throw new Exception("计算异常！", e);//将此处e带上可看到 / by zero错误打印，即真正错误提示！
		}
	}
	
	public void exceptionCatch() throws Exception{
		try {
			this.exceptionThrow();
		} catch (Exception e) {
			LOGGER.info("exceptionCatch异常！" , e);
			throw new Exception("exceptionCatch异常！" + e.toString(), e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new ExceptionTest().exceptionCatch();
	}
}

