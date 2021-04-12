package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.UserTokenKey;
import com.jd.wego.service.ArticleService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/insert")
    @ResponseBody
    public Result<Boolean> insertArticle(HttpServletRequest request, @RequestBody Article article){
        LoginController loginController = new LoginController();
        String token = loginController.getUserToken(request, LoginController.USER_TOKEN);
        User user = jedisService.getKey(UserTokenKey.userTokenKey, token, User.class);
        if(user == null){
            System.out.println("用户未登录.......");
            return Result.error(CodeMsg.ERROR);
        }
        log.info(user.toString());
        articleService.insertArticle(article);
        return Result.success(true);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> editArticle(Article article){
        articleService.updateArticle(article);
        return Result.success(true);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteArticle(int articleId){
        articleService.deleteArticle(articleId);
        return Result.success(true);
    }


}
