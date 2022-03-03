package com.cc.bootstrap.intl.demo.file.download;

import com.cc.bootstrap.common.exception.FileException;
import com.cc.bootstrap.common.schema.FileInfoEntity;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.intl.logic.FileInfoLogic;
import com.cc.bootstrap.page.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 文件下载服务类
 * @author: ChenChen
 * @date: 2022/3/2 16:59
 */
@Component
public class FileDownloadService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileDownloadService.class);

    @Value("${fileupload.path}")
    private String fileUploadPath;

    @Autowired
    private FileInfoLogic fileInfoLogic;

    @Autowired
    private UserMapper userMapper;


    /**
     * @Description 多个文件下载
     * @param userId
     * @param response
     * @author ChenChen
     * @return void
     * @date 2022/3/2 17:12
     */
    @Transactional
    public void downloadFiles(String userId, HttpServletResponse response) {

        // 目录创建失败，不上传
        String dirPath = fileUploadPath + File.separator + userId;
        File dir = new File(dirPath);
        if(!dir.isDirectory() || dir.listFiles() == null || dir.listFiles().length == 0) {
            LOGGER.error("没有目录【{}】或目录中没有文件", dirPath);
            throw new UnsupportedOperationException("为上传文件创建目录失败！");
        }

        // 查询用户信息
        User user = userMapper.selectById(userId);
        if(null == user) {
            LOGGER.error("查不到用户信息！userId 【{}】", userId);
            throw new UnsupportedOperationException("查不到用户信息！");
        }

        // 根据用户id查询带下载文件列表
        List<FileInfoEntity> fileInfoEntityList = fileInfoLogic.findFilesByUserId(userId);
        if(CollectionUtils.isEmpty(fileInfoEntityList)) {
            throw new UnsupportedOperationException("当前用户【{}】无待下载文件！");
        }

        // 转换为VO
        List<FileVo> fileVoList = fileInfoEntityList.stream().map(fileInfoEntity -> {
            FileVo fileVo = new FileVo();
            BeanUtils.copyProperties(fileInfoEntity, fileVo);
            fileVo.setDirPath(dirPath);
            fileVo.setFilePath(dirPath + File.separator + fileVo.getId());
            return fileVo;
        }).collect(Collectors.toList());

        // 文件下载
        try {
            fileInfoLogic.downloadZipFile(fileVoList, user.getUserName() + "文件下载", response);
        } catch (Exception e) {
            LOGGER.error("用户【{}】文件下载失败！", user.getUserName(), e);
            throw new FileException("文件下载失败！");
        }
    }
}
