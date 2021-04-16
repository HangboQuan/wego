package com.jd.wego.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hbquan
 * @date 2021/4/16 17:24
 * 作用是：配置定时任务相关
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_QUARTZ = "LIKE_TASK_QUARTZ";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .withIntervalInHours(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_QUARTZ)
                .withSchedule(scheduleBuilder)
                .build();
    }


}
