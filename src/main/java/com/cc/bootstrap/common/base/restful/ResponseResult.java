package com.cc.bootstrap.common.base.restful;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @ClassName: ResponseResult
 * @Description: Restful API响应结果模板类
 * @author: Administrator
 * @date: 2021年4月25日 上午10:58:52
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
	
	private Boolean success;//返回请求状态，true:请求成功，false：请求失败
	
	private String code;//返回请求结果业务码
	
	private String message;//返回描述信息
	
	private T data;//返回内容体

	public ResponseResult(Boolean success, String code, String message, T data) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}


	//成功，仅提示成功
	public static<T> ResponseResult<T> success(){
		return new ResponseResult<T>(true, null, null, null);
	}
	
	//成功，带成功提示
	public static<T> ResponseResult<T> success(IBaseEnum code){
		return new ResponseResult<T>(true, code.getCode(), code.getMessage(), null);
	}
	
	//成功，带返回数据
	public static<T> ResponseResult<T> success(IBaseEnum code, T data){
		return new ResponseResult<T>(true, code.getCode(), code.getMessage(), data);
	}

	//成功，仅带返回数据
	public static<T> ResponseResult<T> success(T data){
		return new ResponseResult<T>(true, null, null, data);
	}
	
	//失败，不带返回数据
	public static<T> ResponseResult<T> fail(IBaseEnum code){
		return new ResponseResult<T>(false, code.getCode(), code.getMessage(), null);
	}
	
	//失败，带返回数据
	public static<T> ResponseResult<T> fail(IBaseEnum code, T data){
		return new ResponseResult<T>(false, code.getCode(), code.getMessage(), data);
	}
	
	//自定义message，并返回失败
	public static<T> ResponseResult<T> fail(IBaseEnum code, String message){
		return new ResponseResult<T>(false, code.getCode(), message, null);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
