package com.jd.wego.controller;

import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventProducer;
import com.jd.wego.async.EventType;
import com.jd.wego.entity.Article;
import com.jd.wego.entity.Comment;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.LikeKey;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.CommentService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/4/17 17:11
 */
@Controller
public class CommentController {

    @Autowired
    LoginController loginController;

    @Autowired
    JedisService jedisService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    EventProducer eventProducer;

    /**
     * 在文章的详情页进行评论
     */
    @GetMapping("/comment")
    @ResponseBody
    public Result<List<Comment>> likeArticle(HttpServletRequest request, int articleId){
        User user = loginController.getUserInfo(request);
        if(user == null){
            return Result.error(CodeMsg.ERROR);
        }
        return null;
    }
}
