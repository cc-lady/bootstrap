package com.cc.bootstrap.intl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.bootstrap.common.schema.FileInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 文件持久化类
 *
 * @author ChenChen
 * @return 
 * @date 2022/3/2 17:37
 */
@Mapper
public interface FileInfoDao extends BaseMapper<FileInfoEntity> {

}
