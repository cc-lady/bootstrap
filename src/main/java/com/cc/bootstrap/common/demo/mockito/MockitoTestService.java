package com.cc.bootstrap.common.demo.mockito;

import com.cc.bootstrap.page.feign.CustomConfigurationFeign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description TODO
 * @createTime 2021年08月16日 14:45:00
 */
@Service
public class MockitoTestService {

    private static Logger LOGGER = LoggerFactory.getLogger(MockitoTestService.class);

    @Autowired
    private CustomConfigurationFeign customConfigurationFeign;

    //简单提供一个服务：判定获取到了返回true，否则返回false
    public Boolean isExistConfig() {
        String config = null;
        try {
            config = customConfigurationFeign.getCustomConfigs();
        }catch (Exception e) {
            LOGGER.error("调用配置中心获取配置失败！请检查该服务。", e);
        }

        return StringUtils.isNotEmpty(config);
    }

}
