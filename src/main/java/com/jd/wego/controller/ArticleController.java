package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.service.ArticleService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/4/7 15:54
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    JedisService jedisService;

    @Autowired
    LoginController loginController;

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/insert")
    @ResponseBody
    public Result<Boolean> insertArticle(HttpServletRequest request, @RequestBody Article article){
        User user = loginController.getUserInfo(request);
        if(user == null){
            log.info("用户未登录");
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        articleService.insertArticle(article);
        return Result.success(true);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> editArticle(HttpServletRequest request, @RequestBody Article article){
        User user = loginController.getUserInfo(request);
        if(user == null){
            log.info("用户未登录");
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        articleService.updateArticle(article);
        return Result.success(true);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteArticle(HttpServletRequest request, int articleId){
        User user = loginController.getUserInfo(request);
        if(user == null){
            log.info("用户未登录");
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        articleService.deleteArticle(articleId);
        return Result.success(true);
    }


    @GetMapping("/search")
    public Result<List<Article>> searchArticle(String keyword){
        List<Article> articleList = articleService.selectArticleByKeyword(keyword);
        return Result.success(articleList);
    }

}
