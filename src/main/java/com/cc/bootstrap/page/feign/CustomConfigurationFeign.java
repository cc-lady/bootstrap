package com.cc.bootstrap.page.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description TODO
 * @createTime 2021年08月16日 09:15:00
 */
@FeignClient(name = "CUSTOMCONFIGURATION", url = "${feign.client.customconfiguration}")
public interface CustomConfigurationFeign {
    
    /**
     * @description 获取自定义配置
     * @author ChenChen 
     * @param 
     * @updateTime 2021/8/16 9:53 
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/customconfig-test/mycustomconfigs")
    String getCustomConfigs() throws JsonProcessingException;
}
