package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import com.jd.wego.service.ArticleService;
import com.jd.wego.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hbquan
 * @date 2021/4/7 15:54
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/insert")
    @ResponseBody
    public Result<Boolean> insertArticle(Article article){
        articleService.insertArticle(article);
        return Result.success(true);
    }

    @GetMapping("/edit")
    @ResponseBody
    public Result<Boolean> editArticle(Article article){
        articleService.updateArticle(article);
        return Result.success(true);
    }

    @GetMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteArticle(int articleId){
        articleService.deleteArticle(articleId);
        return Result.success(true);
    }


}
