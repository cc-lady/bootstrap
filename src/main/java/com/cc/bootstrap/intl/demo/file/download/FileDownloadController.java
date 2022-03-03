package com.cc.bootstrap.intl.demo.file.download;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 文件下载控制类
 *
 * @author ChenChen
 * @return
 * @date 2022/3/3 9:17
 */
@RestController
@RequestMapping(value = "/api/file/download")
public class FileDownloadController {
    private static Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);

    @Autowired
    private FileDownloadService fileDownloadService;

    @PostMapping(value = "/files")
    public ResponseResult downloadFiles(@RequestParam(name = "userId") String userId,
                                        HttpServletResponse response) {
        // 下载多个文件
        fileDownloadService.downloadFiles(userId, response);
        LOGGER.info("下载多个文件-操作成功！");
        return ResponseResult.success();
    }
}
