package com.jd.wego.async.handler;

import com.jd.wego.async.EventHandler;
import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventType;
import com.jd.wego.entity.Article;
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
 * @date 2021/4/14 15:53
 */
@Component
public class LikeHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(LikeHandler.class);

    @Autowired
    JedisService jedisService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    NoticeService noticeService;

    /**
     * 主要是完成通知，通知用户谁给给点了赞
     *
     * @param eventModel
     */
    @Override
    public void doHandler(EventModel eventModel) {
        // 假入用户自己现在对一篇文章进行了点赞，那么此时fromId就是用户本身
        String fromId = eventModel.getActorId();
        User user = userService.selectByUserId(fromId);
        // 假如用户现在对一篇文章进行了点赞，那么此时toId就是发表该篇文章的作者
        String toId = eventModel.getEntityOwnerId();
        // 这里要通过用户的Id来关联文章，来获取到用户发表的文章，获取文章的标题信息
        int articleId = Integer.valueOf(eventModel.getExts("articleId"));
        logger.info(toId);
        logger.info(String.valueOf(articleId));

        Article article = articleService.selectArticleByTwoUserId(articleId);
        logger.info("文章信息为:{}", article);

        Notice notice = new Notice();
        notice.setFromId(fromId);
        notice.setToId(toId);
        notice.setCreatedDate(new Date());
        notice.setContent(user.getNickname() + "点赞了您的文章：" + article.getArticleTitle());
        notice.setConversationId(fromId + "_" + toId);
        logger.info("notice:{}", notice);
        noticeService.insertNotice(notice);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        // 只关注点赞的事件
        return Arrays.asList(EventType.LIKE);
    }
}
