package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.Schema;

/**
 * @ClassName: Email 
 * @Description: 页面配置发送的email列表实体类
 * @author CC  
 * @date 2018年11月30日 下午3:05:22 
 * @version V1.0 
 */
@TableName(value = "t_email", resultMap = "emails")//mybatis-plus 注解
public class Email implements Schema {

	private static final long serialVersionUID = 1L;
	
	//编号
	@TableId(value = "id")
	private Integer id;
	//用户名
	@TableField(value = "user_name")
	private String userName;
	//email
	private String email;
	//角色
	private String role;
	
	public Email() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", userName=" + userName + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
