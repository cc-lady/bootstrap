package com.cc.bootstrap.common.config;

import org.springframework.boot.CommandLineRunner;

/**
 * 程序启动后从数据库获取之前停止的任务，继续运行
 * 注：仅示例，暂无相关业务数据
 * 主要运用：CommandLineRunner
 */
public class ApplicationInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //1.从数据库获取现有任务
        //List = query
        //2.判断状态分别处理
//        for(QuartzJob job : jobs) {
//            jobService.schedulerJob(job);
//            if (JobStatus.PAUSED.getStatus().equals(job.getTriggerState())) {
//                scheduler.pauseJob(new JobKey(job.getJobName(), job.getJobGroup()));
//            }
//        }
    }
}
