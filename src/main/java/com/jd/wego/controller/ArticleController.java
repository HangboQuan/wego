package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import com.jd.wego.entity.Category;
import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.CategoryService;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import com.jd.wego.vo.ArticleUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/4/7 15:54
 *
 * 设计：
 * 当用户发表一篇文章的时候，成就值+10分
 * 当用户的文章被别人点赞一次之后，成就值+5分
 * 当用户的文章被别人评论一次之后，成就值+5分
 * 当用户被一个人关注后，成就值+10分
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/insert")
    @ResponseBody
    public Result<Boolean> insertArticle(HttpServletRequest request, @RequestBody Article article){
        User user = loginController.getUserInfo(request);
        if(user == null){
            log.info("用户未登录");
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        log.info(article.toString());
        // 需要通过闯过来的categoryName来查找出categoryId
        Category category = categoryService.selectCategoryByName(article.getArticleCategoryName());
        article.setArticleCategoryId(category.getCategoryId());
        article.setCreatedTime(new Date());
        article.setUpdateTime(new Date());

        // 发表一篇文章用户的成就值+10分
        user.setAchieveValue(user.getAchieveValue() + 10);
        userService.updateByUserId(user);
        articleService.insertArticle(article);

        return Result.success(true);
    }

/*    @PostMapping("/insert1")
    @ResponseBody
    public Result<Boolean> insertArticle1(HttpServletRequest request, @RequestParam Map<String,Object> map){
        User user = loginController.getUserInfo(request);
        if(user == null){
            log.info("用户未登录");
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        for(Map.Entry<String, Object> objs : map.entrySet()){
            Article article = new Article();
            if(objs.getKey().equals("articleTitle")){
                article.setArticleTitle((String)objs.getValue());
            } else if(objs.getKey().equals("articleContent")){
                article.setArticleContent((String)objs.getValue());
            }else if(objs.getKey().equals("articleCategoryId")){
                article.setArticleCategoryId((int)objs.getValue());
            }
            articleService.insertArticle(article);
        }

        return Result.success(true);
    }*/




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
    @ResponseBody
    public Result<List<ArticleUserVo>> searchArticle(String keyword){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 该方法是调用ElasticSearch的接口来查询的
        List<ArticleUserVo> articleList = articleService.selectArticleByKeywords(keyword);
        stopWatch.stop();
        log.info("使用ES来搜索文章的耗时为：{}ms",stopWatch.getTotalTimeMillis());
        return Result.success(articleList);
    }

    /**
     * 这个代码并无和业务代码相关，但是是为了测试下同等情况下和ES的查询效率哪个更高
     * @param keyword
     * @return
     */
    @GetMapping("/search/mysql")
    @ResponseBody
    public Result<List<ArticleUserVo>> searchArticleByMysql(String keyword){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 该方法是调用ElasticSearch的接口来查询的
        List<ArticleUserVo> articleList = articleService.selectArticleByKeyword(keyword);
        stopWatch.stop();
        log.info("使用Mysql的模糊查询来搜索文章的耗时为：{}ms",stopWatch.getTotalTimeMillis());
        return Result.success(articleList);
    }


    @GetMapping("/hotspot")
    @ResponseBody
    public Result<List<Article>> hostSpotArticle(){
        List<Article> articleList = articleService.selectArticleByViewCount();
        return Result.success(articleList);
    }



}
