package com.cc.bootstrap.intl.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.bootstrap.common.schema.FileInfoEntity;
import com.cc.bootstrap.intl.dao.FileInfoDao;
import com.cc.bootstrap.intl.demo.file.download.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
     * @param userId
     * @param fileName
     * @param fileSize
     * @param uploadTime
     * @author ChenChen
     * @return void
     * @date 2022/3/2 17:44
     */
    @Transactional
    public void insertUploadFileInfo(String fileId, String userId, String fileName, String fileSize, Date uploadTime) {
        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setId(fileId);
        fileInfoEntity.setUserId(userId);
        fileInfoEntity.setFileName(fileName);
        fileInfoEntity.setFileSize(fileSize);
        fileInfoEntity.setUploadTime(uploadTime);
        fileInfoDao.insert(fileInfoEntity);
        LOGGER.info("插入文件上传信息-操作成功！");
    }

    /**
     * @Description 根据用户查询文件列表
     * @param userId
     * @author ChenChen
     * @return java.util.List<com.cc.bootstrap.common.schema.FileInfoEntity>
     * @date 2022/3/3 9:36
     */
    public List<FileInfoEntity> findFilesByUserId(String userId) {
        QueryWrapper<FileInfoEntity> queryWrapper_fileInfoEntity = new QueryWrapper<>();
        queryWrapper_fileInfoEntity.eq("user_id", userId);
        return fileInfoDao.selectList(queryWrapper_fileInfoEntity);
    }

    /**
     * @Description 文件下载
     * @param fileVoList
     * @param zipName
     * @param response
     * @author ChenChen
     * @return void
     * @date 2022/3/3 9:51
     */
    public void downloadZipFile(List<FileVo> fileVoList, String zipName, HttpServletResponse response)
            throws IOException {
        // 设置压缩包名称
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String downloadDateStr = simpleDateFormat.format(new Date());
        String zipFileName = zipName + "-" + downloadDateStr + ".zip";

        // 设置下载返回头
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(zipFileName, "UTF-8"));
        response.setHeader("FileName", URLEncoder.encode(zipFileName, "UTF-8"));
        response.setHeader("Access-Control-Expose-Headers", "FileName");
        response.setContentType("application/octet-stream");

        // 开始下载
        Map<String, Integer> fileNameMap = new HashMap<>();//解决重名文件无法下载问题
        String zipEntryName = null;

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        FileSystemResource resource = null;
        for (FileVo fileVo : fileVoList) {
            // 下载的文件不在jar包内部
            resource = new FileSystemResource(fileVo.getFilePath());
            // 若下载的文件在jar包内部，需要改变写法，否则下载错误
//            ResourceLoader resourceLoader = new DefaultResourceLoader();
//            Resource resource = resourceLoader.getResource(fileVo.getFilePath());
            if(!resource.exists()) {
                LOGGER.error("文件【{}】不存在！", fileVo.getFileName());
                continue;
            }

            // 重名时压缩包中显示名称加(1)类似格式-拼接
            zipEntryName = this.generateMultiFileName(fileVo, fileNameMap);

            // 压缩包中每个文件
            ZipEntry zipEntry = new ZipEntry(zipEntryName);
            zipEntry.setSize(resource.contentLength());
            zipOutputStream.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOutputStream);
            zipOutputStream.closeEntry();
        }

        zipOutputStream.finish();
        zipOutputStream.close();
    }

    /**
     * @Description 重名时压缩包中显示名称加(1)类似格式-拼接
     * @param fileVo
     * @param fileNameMap
     * @author ChenChen
     * @return java.lang.String
     * @date 2022/3/3 10:01
     */
    private String generateMultiFileName(FileVo fileVo, Map<String, Integer> fileNameMap) {
        // 重名时打上记号
        String fileName = fileVo.getFileName();
        if(null == fileNameMap.get(fileName)) {
            fileNameMap.put(fileName, 0);
        } else {
            fileNameMap.put(fileName, fileNameMap.get(fileName) + 1);
        }

        Integer suffix = fileNameMap.get(fileName);
        if(0 == suffix) {// 不重名时不用拼接后缀
            return fileName;
        }

        // 重名时压缩包中显示名称加(1)类似格式-拼接，例如：file(1).txt
        String suffixStr = '(' + String.valueOf(suffix) + ')';
        return fileName.substring(0, fileName.lastIndexOf('.'))
                + suffixStr
                + fileName.substring(fileName.lastIndexOf('.'));
    }
}
