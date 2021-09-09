package com.cc.bootstrap.common.base.restful;

/**
 * @ClassName: IBaseEnum
 * @Description: 相应状态码及信息--基础枚举接口
 * @author: Administrator
 * @date: 2021年4月25日 上午11:05:56
 */
public interface IBaseEnum {

	/**
	 * @Title: getCode
	 * @Description: 获取code
	 * @return
	 * @return: String
	 */
	String getCode();
	
	
	/**
	 * @Title: getMessage
	 * @Description: 获取Message
	 * @return
	 * @return: String
	 */
	String getMessage();
}
