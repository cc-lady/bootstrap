package com.cc.bootstrap.intl.demo.file.upload;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.exception.FileException;
import com.cc.bootstrap.common.util.FileUtils;
import com.cc.bootstrap.intl.logic.FileInfoLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 文件上传服务类
 * @author: ChenChen
 * @date: 2022/3/2 16:59
 */
@Component
public class FileUploadService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    @Value("${fileupload.path}")
    private String fileUploadPath;

    @Autowired
    private FileInfoLogic fileInfoLogic;


    /**
     * @Description 多个文件上传
     * @param userId
     * @param files
     * @author ChenChen
     * @return void
     * @date 2022/3/2 17:12
     */
    @Transactional
    public ResponseResult uploadFiles(String userId, MultipartFile[] files) {

        // 目录创建失败，不上传
        String dirPath = fileUploadPath + File.separator + userId;
        if(!FileUtils.mkdir(dirPath)) {
            throw new UnsupportedOperationException("为上传文件创建目录失败！");
        }

        String fileId = null;
        String fileName = null;
        String fileSize = null;
        Date uploadTime = new Date();
        String filePath = null;
        for (MultipartFile file: files) {
            fileId = UUID.randomUUID().toString();
            fileName = file.getOriginalFilename();
            float fSize = file.getSize() / 1024f;
            if(fSize < 1024) {
                fileSize = String.format("%.2f", fSize) + "KB";
            } else {
                fileSize = String.format("%.2f", fSize/1024) + "MB";
            }

            // 上传D:\\git\\github-repo\\bootstrap-github\\uploadFile\\cc\\f9086ca2-8f99-4466-b91f-43ad1500536d
            // 即不能直接将文件名放在上传路径中，以免文件名../../类似，发生文件路径泄漏安全问题。
            filePath = dirPath + File.separator + fileId;

            // 插入上传文件信息
            try {
                fileInfoLogic.insertUploadFileInfo(fileId, userId, fileName, fileSize, uploadTime);
            } catch (Exception e) {
                LOGGER.error("文件【{}】上传-插入上传文件信息失败！", fileName, e);
                continue;
            }

            // 上传文件
            try {
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                LOGGER.error("文件【{}】上传失败！", fileName, e);
                throw new FileException("上传文件【"+fileName+"】发生异常，请检查！");
            }
        }

        return ResponseResult.success();
    }
}
