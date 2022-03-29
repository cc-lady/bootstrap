package com.cc.bootstrap.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 异步线程池
 * @author: ChenChen
 * @date: 2022/3/29 8:48
 */
@EnableAsync
@Configuration
public class AsyncThreadPoolConfiguration {

    /**
     * @Description 异步线程池说明
     * 当池子小于CorePoolSize核心线程数，就新建线程，处理请求；
     * 当池子大于等于CorePoolSize核心线程数，就放入缓冲队列处理；
     * 大于QueueCapacity缓冲队列容量时，新建线程处理，
     * 再大于MaxPoolSize最大线程数，则使用拒绝策略处理，此异步线程池使用拒绝策略，放弃任务。（可根据需要配置不同的拒绝策略）
     *
     * @author ChenChen
     * @return java.util.concurrent.Executor
     * @date 2022/3/29 8:52
     */
    @Bean("asyncThreadPool")
    public Executor asyncThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 拒绝策略：放弃当前任务，且抛出异常（可根据需要配置不同的拒绝策略）
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setCorePoolSize(5);//核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(15);//最大线程数
        threadPoolTaskExecutor.setQueueCapacity(10);//缓冲队列容量
        threadPoolTaskExecutor.setThreadNamePrefix("single-run-test-");//线程名称前缀
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
