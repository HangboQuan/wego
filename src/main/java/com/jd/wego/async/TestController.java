package com.jd.wego.async;

import com.jd.wego.entity.Article;
import com.jd.wego.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import static com.jd.wego.async.EventType.LIKE;

/**
 * @author hbquan
 * @date 2021/4/14 17:08
 */
@Controller
public class TestController {

    @Autowired
    EventProducer eventProducer;

    @Autowired
    ArticleService articleService;

    @GetMapping("/hello/{articleId}")
    @ResponseBody
    public String hello(@PathVariable("articleId") int articleId){
        EventModel eventModel = new EventModel();
        eventModel.setEventType(LIKE).setActorId("18392710807").setEntityId(2).setEntityType(3).setEntityOwnerId("18392710807")
            .setExts("articleId", articleId + "");
        eventProducer.fireEvent(eventModel);
        return "i love you";
    }

    @GetMapping("/hi")
    @ResponseBody
    public void hello(){
        List<Article> articleList =  articleService.selectAllArtilce();
        for(Article article : articleList){
            System.out.println(article);
        }
    }


}
