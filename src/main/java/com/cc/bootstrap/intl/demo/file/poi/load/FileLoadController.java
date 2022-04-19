package com.cc.bootstrap.intl.demo.file.poi.load;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description poi文件导出控制类
 *
 * @author ChenChen
 * @return
 * @date 2022/4/19 14:56
 */
@RestController
@RequestMapping(value = "/api/poi/load")
public class FileLoadController {
    private static Logger LOGGER = LoggerFactory.getLogger(FileLoadController.class);

    @Autowired
    private FileLoadService fileLoadService;

    @GetMapping(value = "/{userId}")
    public ResponseResult downloadFiles(@PathVariable(name = "userId") String userId,
                                        HttpServletResponse response) {
        // 导出用户数据为excel
        try {
            fileLoadService.load(userId, response);
        } catch (IOException e) {
            LOGGER.error("poi导出用户数据失败！" , e);
            throw new FileException("poi导出用户数据失败！", e);
        }

        LOGGER.info("poi导出用户数据成功！");
        return null;
    }


}
