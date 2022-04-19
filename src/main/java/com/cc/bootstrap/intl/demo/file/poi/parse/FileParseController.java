package com.cc.bootstrap.intl.demo.file.poi.parse;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.exception.FileException;
import com.cc.bootstrap.common.schema.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Description: poi文件解析控制类
 * @author: ChenChen
 * @date: 2022/4/19 9:28
 */
@RestController
@RequestMapping(value = "/api/poi/parse")
public class FileParseController {
    private static Logger LOGGER = LoggerFactory.getLogger(FileParseController.class);

    @Autowired
    private FileParseService fileParseService;

    @GetMapping(value = "/{userId}")
    public ResponseResult downloadFiles(@PathVariable(name = "userId") String userId) {
        // 解析对应上传的文件，返回解析的结果
        List<User> userList = null;
        try {
            userList = fileParseService.parse(userId);
        } catch (IOException e) {
            LOGGER.error("文件解析失败！" , e);
            throw new FileException("poi解析文件失败！", e);
        }
        LOGGER.info("poi解析文件成功！");
        return ResponseResult.success(userList);
    }

}
