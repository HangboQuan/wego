package com.jd.wego.controller;

import com.jd.wego.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/7 20:25
 */
@Controller
public class IndexController {

    @Autowired
    QueryArticleByEsService queryArticleByEsService;

    @RequestMapping("/hello")
    @ResponseBody
    public void hello(){
        List<Article> articleList = queryArticleByEsService.selectAllArticle();
        for(Article article : articleList){
            System.out.println(article);
        }
    }
}
