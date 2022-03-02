package com.cc.bootstrap.intl.demo.file.upload;

import com.cc.bootstrap.common.schema.FileInfoEntity;
import com.cc.bootstrap.intl.dao.FileInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 文件信息逻辑类
 * @author: ChenChen
 * @date: 2022/3/2 17:43
 */
@Component
public class FileInfoLogic {
    private static Logger LOGGER = LoggerFactory.getLogger(FileInfoLogic.class);

    @Autowired
    private FileInfoDao fileInfoDao;

    /**
     * @Description 插入文件上传信息
     * @param fileId
     * @param fileName
     * @param fileSize
     * @param uploadTime
     * @author ChenChen
     * @return void
     * @date 2022/3/2 17:44
     */
    public void insertUploadFileInfo(String fileId, String fileName, String fileSize, Date uploadTime) {
        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setId(fileId);
        fileInfoEntity.setFileName(fileName);
        fileInfoEntity.setFileSize(fileSize);
        fileInfoEntity.setUploadTime(uploadTime);
        fileInfoDao.insert(fileInfoEntity);
        LOGGER.info("插入文件上传信息-操作成功！");
    }
}
