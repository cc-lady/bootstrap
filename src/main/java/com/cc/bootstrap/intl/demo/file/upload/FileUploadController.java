package com.cc.bootstrap.intl.demo.file.upload;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 文件上传控制类
 *
 * @author ChenChen
 * @return
 * @date 2022/3/2 16:53
 */
@RestController
@RequestMapping(value = "/api/file/upload")
public class FileUploadController {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/files")
    public ResponseResult uploadFiles(@RequestParam(name = "userId") String userId,
                                      @RequestParam(name = "files") MultipartFile[] files) {
        // 上传多个文件
        fileUploadService.uploadFiles(userId, files);
        LOGGER.info("上传多个文件-操作成功！");
        return ResponseResult.success();
    }
}
