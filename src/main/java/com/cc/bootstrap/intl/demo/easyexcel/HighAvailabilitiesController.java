package com.cc.bootstrap.intl.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.cc.bootstrap.common.base.restful.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 高保障目录服务 - 主要用于演示easyexcel
 * @author: ChenChen
 * @date: 2025-08-19 9:33
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class HighAvailabilitiesController {

    @Autowired
    private HighAvailabilitiesService highAvailabilitiesService;
    @Autowired
    private LocalValidatorFactoryBean validator;
    @Resource(name = "asyncThreadPool")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * @Description easyexcel 批量导入
     * @param file
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult
     * @date 2025-08-19 10:17
     */
    @PostMapping(value = "/high-availabilities/import")
    public ResponseResult importByFile(@RequestParam("file")CommonsMultipartFile file) {
        // 导入
        HighAvailabilitiesImportListener importListener = new HighAvailabilitiesImportListener(
                highAvailabilitiesService, validator,  threadPoolTaskExecutor);

        try {
            EasyExcel.read(file.getInputStream(), HighAvailabilitiesDto.class, importListener);
        } catch (Exception e) {
            return ResponseResult.failWithMessage("500", String.format("高保障表目录导入失败：%s", e.getMessage()));
        }

        // 导入失败行
        List<HighAvailabilitiesDto> errorList = importListener.getErrorList();
        // 预期异常，返回失败信息
        if (CollectionUtils.isNotEmpty(errorList)) {
            return ResponseResult.fail("400", "高保障表目录导入失败", errorList);
        }

        // 导入成功
        return ResponseResult.success();
    }


}
