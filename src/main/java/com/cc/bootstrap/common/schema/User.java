package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.AbstractEntity;
import lombok.Data;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @author: Administrator
 * @date: 2021年4月25日 上午9:43:56
 */
@Data
@TableName(value = "t_user", resultMap = "users")//mybatis-plus 注解
public class User extends AbstractEntity {

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

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", mobilePhone=" + mobilePhone
				+ ", address=" + address + ", role=" + role + ", note=" + note + ", email=" + email + "]";
	}
	
}
