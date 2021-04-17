package com.jd.wego.task;

import com.jd.wego.controller.LikeController;
import com.jd.wego.service.LikeService;
import com.jd.wego.service.impl.LikeServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/16 20:39
 */
@Component
public class LikeTask extends QuartzJobBean {

    @Autowired
    LikeService likeService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(LikeTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("LikeTask--------------------------{}", sdf.format(new Date()));
        likeService.transLikedCountFromRedis2DB();
    }
}
