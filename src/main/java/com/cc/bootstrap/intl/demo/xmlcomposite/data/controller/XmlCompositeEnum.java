package com.cc.bootstrap.intl.demo.xmlcomposite.data.controller;

import com.cc.bootstrap.common.base.restful.IBaseEnum;

/**
 * @ClassName: XmlCompositeEnum
 * @Description: XML服务返回码
 * @author: Administrator
 * @date: 2021年4月25日 下午2:10:20
 */
public enum XmlCompositeEnum implements IBaseEnum{//使用 enum 定义的枚举类默认继承了 java.lang.Enum 类，并实现了 java.lang.Seriablizable 和 java.lang.Comparable 两个接口;

	//======enum枚举内容要放在上面，否则会编译报错======
	/*
	 * 枚举类的所有实例(枚举值)必须在枚举类的第一行显式地列出，否则这个枚举类将永远不能产生实例。
	 * 列出这些实例(枚举值)时，系统会自动添加 public static final 修饰，无需程序员显式添加。
	 */
	//1.成功状态码 1XXX
	SUCCESS_XML("XML-1000", "成功请求"),//所有的枚举值都是 public static final 的，且非抽象的枚举类不能再派生子类
	//2.失败状态码 2XXX
	FAIL_XML("XML-2000","请求失败"),
	;

	private String code;
	private String message;

	private XmlCompositeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}