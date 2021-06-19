package com.jd.wego.service.impl;

import com.jd.wego.entity.Article;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.LikeKey;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Set;

/**
 * @author hbquan
 * @date 2021/4/15 20:51
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    JedisService jedisService;

    @Autowired
    ArticleService articleService;

    private static final Logger log = LoggerFactory.getLogger(LikeServiceImpl.class);

    @Override
    public long like(String key, String userId) {
        jedisService.sadd(key, userId);
        return jedisService.scard(key);
    }

    @Override
    public long dislike(String key, String value) {
        jedisService.srem(key, value);
        return jedisService.scard(key);
    }

    @Override
    public long likeCount(String key) {
        return jedisService.scard(key);
    }

    @Override
    public Set<String> likeCountUserId(String key) {
        return jedisService.smembers(key);
    }

    @Override
    public void transLikedCountFromRedis2DB() {
        /**
         * 从Redis中拿到以"LikeKey:like+articleId为key的key，根据这些key就可以拿到具体的文章Id，就
         * 可以有针对性的更新有点赞的文章，优化了以前遍历所有的文章来进行更新。
         * 经过分析，下面的代码耗时就比较小了"
         */
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> matchLikeKey = jedisService.scan(LikeKey.LIKE_KEY.getPrefix() + "*");
        for(String s : matchLikeKey){
            // 这里从LikeKey:like是12位数，所以12位开始遍历文章ID
            int articleId = Integer.valueOf(s.substring(12));
            Article article = articleService.selectArticleByArticleId(articleId);
            long likeCount = jedisService.scard(s);
            article.setArticleLikeCount((int) likeCount);
            articleService.updateArticle(article);
        }
        stopWatch.stop();
        log.info("每隔两小时将Redis的点赞数量更新至DB中,耗时{}ms", stopWatch.getTotalTimeMillis());


    }


}
