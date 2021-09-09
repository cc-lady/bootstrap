package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.Schema;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @author: Administrator
 * @date: 2021年4月25日 上午9:43:56
 */
@TableName(value = "t_user", resultMap = "users")//mybatis-plus 注解
public class User implements Schema {

	private static final long serialVersionUID = 1L;
	
	//编号
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	//用户名
	@TableField(value = "user_name")
	private String userName;
	//密码
	@TableField(value = "pwd")
	private String password;
	//电话
	@TableField(value = "mobile_phone")
	private String mobilePhone;
	//地址

	private String address;
	//角色

	private Integer role;
	//备注

	private String note;
	//email

	private String email;
	public User(){}
	
	public User(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public User(String userName, String password, String mobilePhone, String address, Integer role, String note,
			String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.role = role;
		this.note = note;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", mobilePhone=" + mobilePhone
				+ ", address=" + address + ", role=" + role + ", note=" + note + ", email=" + email + "]";
	}
	
}
