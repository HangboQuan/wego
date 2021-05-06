package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import com.jd.wego.service.ArticleService;
import com.jd.wego.utils.Result;
import com.jd.wego.vo.ArticleUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/7 20:25
 */
@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    /**
     * 首页
     */
    @GetMapping("/")
    @ResponseBody
    public Result<List<ArticleUserVo>> index(){
        List<ArticleUserVo> articleList = articleService.selectAllArticleIndexViewData();
        return Result.success(articleList);
    }

    /**
     * 文章的详情页
     * @param articleId
     * @return
     */
    @GetMapping("/detail/{articleId}")
    @ResponseBody
    public Result<ArticleUserVo> articleDetail(@PathVariable("articleId") int articleId){

        //用户请求一次这个接口，相当于用户浏览量+1
        Article article = articleService.selectArticleByArticleId(articleId);
        article.setArticleViewCount(article.getArticleViewCount() + 1);
        articleService.updateArticle(article);
        ArticleUserVo articleUserVo = articleService.selectAllArticleDetail(articleId);

        return Result.success(articleUserVo);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseBody
    public Result<List<ArticleUserVo>> articleCategory(@PathVariable("categoryId") int categoryId){
        List<ArticleUserVo> articleList = articleService.selectAllArticleCategoryData(categoryId);
        return Result.success(articleList);
    }
}
