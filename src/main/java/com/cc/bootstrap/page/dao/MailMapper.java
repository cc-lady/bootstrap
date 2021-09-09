package com.cc.bootstrap.page.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.bootstrap.common.schema.Email;

/**
 * @ClassName: MailMapper 
 * @Description: mail实体类查询映射方法 
 * @author CC  
 * @date 2018年11月30日 下午3:07:41 
 * @version V1.0 
 */
@Mapper
public interface MailMapper extends BaseMapper<Email> {
	//查找所有的emails
	List<Email> findAllEmails();
	
	//插入一条email
	void insertEmail(@Param("email")Email email);
}
