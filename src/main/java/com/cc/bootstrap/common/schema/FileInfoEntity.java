package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.Schema;
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
public class FileInfoEntity implements Schema {
    private static final long serialVersionUID = 1L;

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
}
