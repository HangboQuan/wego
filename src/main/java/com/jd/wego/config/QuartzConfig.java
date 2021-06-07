package com.jd.wego.config;

import com.jd.wego.task.AchieveValueTask;
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
    private static final String ACHIEVE_VALUE_SORT_QUARTZ = "ACHIEVE_VALUE_SORT_QUARTZ ";

    @Bean
    public JobDetail quartzDetail() {
        // Jobs added with no trigger must be durable.
        // 这里的意思就是如果没有添加触发器，那么必须就要是持续的,所以这里就要设置storeDurably
        return JobBuilder.newJob(LikeTask.class).storeDurably().build();
    }

    @Bean
    public JobDetail quartzDetail1() {
        // Jobs added with no trigger must be durable.
        // 这里的意思就是如果没有添加触发器，那么必须就要是持续的,所以这里就要设置storeDurably
        return JobBuilder.newJob(AchieveValueTask.class).storeDurably().build();
    }


    @Bean
    public Trigger quartzTrigger() {
        // 目前这里配置的是10s更新一次 现在更新为每2个小时更新下程序
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //.withIntervalInSeconds(30)
                .withIntervalInHours(2)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_QUARTZ)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger quartzTrigger1() {
        // 这里设置的是用户表的成就值的属性，5040h即30天，会自动将用户表的成就值清零
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(5040)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail1())
                .withIdentity(ACHIEVE_VALUE_SORT_QUARTZ)
                .withSchedule(scheduleBuilder)
                .build();
    }


}
