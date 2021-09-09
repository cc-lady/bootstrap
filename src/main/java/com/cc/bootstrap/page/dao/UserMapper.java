package com.cc.bootstrap.page.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.bootstrap.common.schema.User;


//@CacheConfig(cacheNames = "foo")
@Mapper//注解后，spring自动将POJO转换为容器管理的Bean
public interface UserMapper  extends BaseMapper<User>{//BaseMapper  ---使用Mybatis Plus 
	//查询所有用户
	//@Cacheable
	List<User> findAllUsers();

	//登录时查询单个用户
	User findUserForLogin(@Param("user")User user);
}