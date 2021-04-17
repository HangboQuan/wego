package com.jd.wego.config;

import com.jd.wego.task.LikeTask;
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
        // Jobs added with no trigger must be durable.
        // 这里的意思就是如果没有添加触发器，那么必须就要是持续的,所以这里就要设置storeDurably
        return JobBuilder.newJob(LikeTask.class).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        // 目前这里配置的是10s更新一次 现在更新为每2个小时更新下程序
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .withIntervalInHours(2)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_QUARTZ)
                .withSchedule(scheduleBuilder)
                .build();
    }


}
