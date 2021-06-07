package com.jd.wego.async.handler;

import com.jd.wego.async.EventHandler;
import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventType;
import com.jd.wego.entity.Article;
import com.jd.wego.entity.Comment;
import com.jd.wego.entity.Notice;
import com.jd.wego.entity.User;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.CommentService;
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
 * @date 2021/4/14 19:13
 */
@Component
public class CommentHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommentHandler.class);

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    CommentService commentService;

    @Override
    public void doHandler(EventModel eventModel) {

        // 假入用户自己现在对一篇文章进行了评论，那么此时fromId就是用户本身
        String fromId = eventModel.getActorId();
        User user = userService.selectByUserId(fromId);
        // 假如用户现在对一篇文章进行了评论，那么此时toId就是发表该篇文章的作者
        String toId = eventModel.getEntityOwnerId();
        // 这里要通过用户的Id来关联文章，来获取到用户发表的文章，获取文章的标题信息
        int articleId = Integer.valueOf(eventModel.getExts("articleId"));
        Article article = articleService.selectArticleByTwoUserId(articleId);
        logger.info("文章信息为:{}", article);

        int commentId = Integer.valueOf(eventModel.getExts("commentId"));
        Comment comment = commentService.selectCommentById(commentId);
        logger.info("评论信息为:{}", comment);

        Notice notice = new Notice();
        notice.setFromId(fromId);
        notice.setToId(toId);
        notice.setCreatedDate(new Date());
        notice.setContent(user.getNickname() + "评论您的文章：" + article.getArticleTitle() +
                ",评论的内容为：" + comment.getCommentContent());
        notice.setConversationId(fromId + "_" + toId);
        logger.info("notice:{}", notice);
        noticeService.insertNotice(notice);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMNET);
    }
}
