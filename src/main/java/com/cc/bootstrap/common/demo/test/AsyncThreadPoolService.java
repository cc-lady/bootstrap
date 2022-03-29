package com.cc.bootstrap.common.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description: AsyncThreadPoolService 使用异步线程池，不能本类中使用，否则不会有异步效果
 * @author: ChenChen
 * @date: 2022/3/29 9:41
 */
@Service
public class AsyncThreadPoolService {
    private static Logger LOGGER = LoggerFactory.getLogger(AsyncThreadPoolService.class);


    @Async("asyncThreadPool")
    public void asyncThreadPoolTest(String printPrefix) {
        LOGGER.info(">>>>>>>>>>>>>>异步打印开始<<<<<<<<<<<<<");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(printPrefix + i);
        }
        System.out.println(stringBuilder);
        LOGGER.info(">>>>>>>>>>>>>>异步打印结束<<<<<<<<<<<<<");
    }
}
