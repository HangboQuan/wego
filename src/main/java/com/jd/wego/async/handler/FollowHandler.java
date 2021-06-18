package com.jd.wego.async.handler;

import com.jd.wego.async.EventHandler;
import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventType;
import com.jd.wego.entity.Notice;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.NoticeService;
import com.jd.wego.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 11:25
 */
@Component
public class FollowHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(FollowHandler.class);

    @Autowired
    JedisService jedisService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    NoticeService noticeService;

    @Override
    public void doHandler(EventModel eventModel) {
        String fromId = eventModel.getActorId();
        String toId = eventModel.getEntityOwnerId();
        User userFrom = userService.selectByUserId(fromId);
        // 没必要在获取用户本身了，只需要写明谁关注了你
        Notice notice = new Notice();
        notice.setFromId(fromId);
        notice.setToId(toId);
        notice.setContent(userFrom.getNickname() + "关注了用户你");
        notice.setConversationId(fromId + "_" + toId);
        notice.setCreatedDate(new Date());
        notice.setHasRead(0);
        logger.info("notice:{}", notice);
        noticeService.insertNotice(notice);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.FOLLOW);
    }
}
