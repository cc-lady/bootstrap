package com.cc.bootstrap.intl.demo.file.download;

import com.cc.bootstrap.common.base.entity.Schema;
import com.cc.bootstrap.common.schema.FileInfoEntity;
import lombok.Data;

/**
 * @Description: 文件传输类
 * @author: ChenChen
 * @date: 2022/3/3 9:24
 */
@Data
public class FileVo extends FileInfoEntity implements Schema {
    private static final long serialVersionUID = 1L;

    // 文件存储目录路径
    private String dirPath;

    // 文件存储路径
    private String filePath;
}
