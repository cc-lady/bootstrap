package com.cc.bootstrap.page.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.page.dao.UserMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserService
 * @Description: 用户服务类
 * 此类目前仅用来演示mybatis-plus的CRUD功能以及分页功能
 * @author: Administrator
 * @date: 2021年4月25日 上午10:32:30
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public boolean saveUser(User user){
	    return super.save(user);
    }

	@Transactional
	public int saveUser2(User user){
		return userMapper.insert(user);
	}
	
}
