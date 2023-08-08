package com.cc.bootstrap.common.demo.test;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.vo.DbManagerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: 测试获取配置 apache commons configurations2
 * @author: ChenChen
 * @date: 2022/3/29 9:14
 */
@RestController
@RequestMapping(value = "/test/configs")
public class Configurations2Test {
    private static Logger LOGGER = LoggerFactory.getLogger(Configurations2Test.class);

    @Resource(name="dbManagerConfigs")
    private Map<String, DbManagerVO> dbManagerConfigs;

    // get http://localhost:8013/test/configs/beijing
    @GetMapping(value = "/beijing")
    public ResponseResult print() {
        LOGGER.info(">>>>>>>>>>>>>>开始测试获取配置<<<<<<<<<<<<<");
        DbManagerVO beijing = dbManagerConfigs.get("11");
        LOGGER.info(">>>>>>>>>>>>>>测试获取配置请求结束<<<<<<<<<<<<<");
        return ResponseResult.success(beijing);
    }
}
