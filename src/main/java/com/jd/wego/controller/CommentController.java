package com.jd.wego.controller;

import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventProducer;
import com.jd.wego.async.EventType;
import com.jd.wego.entity.Article;
import com.jd.wego.entity.Comment;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.CommentService;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import com.jd.wego.vo.CommentUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/4/17 17:11
 */
@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    LoginController loginController;

    @Autowired
    JedisService jedisService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    EventProducer eventProducer;

    /**
     * 在文章的详情页进行评论, 从前端界面传过来文章id以及评论的内容
     */
    @PostMapping("/insert/comment")
    @ResponseBody
    public Result<Boolean> commentArticle(HttpServletRequest request, @RequestParam(value
            = "articleId", required = false) Integer articleId, @RequestParam(value = "content", required = false) String content) {
        User user = loginController.getUserInfo(request);
        if (user == null) {
            return Result.error(CodeMsg.ERROR);
        }

        // 为了方便前后端联调，先统一将user对象写死
        // User user = userService.selectByUserId("18392710807");
        // 进入到下面来说明用户登录了，将这条评论插入comment表
        Comment comment = new Comment();
        comment.setCommentArticleId(articleId);
        comment.setCommentUserId(user.getUserId());
        comment.setCommentContent(content);
        comment.setCommentCreatedTime(new Date());
        commentService.insertComment(comment);
        // 评论添加成功之后，文章的评论数+1
        Article article = articleService.selectArticleByArticleId(articleId);
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        articleService.updateArticle(article);

        // 然后，需要将评论这个异步通知，发给被评论的用户
        EventModel eventModel = new EventModel();

        String articleAuthor = articleService.selectArticleByTwoUserId(articleId).getArticleUserId();
        // 获取Comment表中最新的comment_id,即表示当前的comment对象
        int commentId = commentService.selectLastInsertCommentId();
        logger.info("评论的id:" + commentId);


        eventModel.setActorId(user.getUserId()).setEntityType(1).setEntityId(1).setEntityOwnerId(articleAuthor)
                .setEventType(EventType.COMMNET).setExts("articleId", articleId + "").
                setExts("commentId", commentId + "");
        // 将该评论异步通知给文章的作者
        eventProducer.fireEvent(eventModel);

        // 获取文章作者信息，然后更新文章的成就值
        User publishUser = userService.selectByUserId(articleAuthor);
        publishUser.setAchieveValue(publishUser.getAchieveValue() + 10);
        userService.updateByUserId(publishUser);


        return Result.success(true);


    }

    @GetMapping("/comment/list")
    @ResponseBody
    public Result<List<CommentUserVo>> commentArticleLists(int articleId) {
        List<CommentUserVo> commentUserVoList = commentService.selectCommentLists(articleId);
        return Result.success(commentUserVoList);
    }
}
