package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 文件信息表
 * @author: ChenChen
 * @date: 2022/3/2 17:07
 */
@Data
@NoArgsConstructor
@TableName(value = "t_file_info")
public class FileInfoEntity extends AbstractEntity {

    //文件编号
    @TableId(value = "id")
    private String id;

    //文件名
    @TableField(value = "file_name")
    private String fileName;

    //文件大小
    @TableField(value = "file_size")
    private String fileSize;

    //文件上传实践
    @TableField(value = "upload_time")
    private Date uploadTime;

    // 例如：cc
    @TableField(value = "user_id")
    private String userId;
}
