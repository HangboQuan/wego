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
        // 现在articleId需要以参数的形式传递进来
//        int articleId = 1;
        // 从redis中获取当前这篇文章的点赞数
//        long likeCount = jedisService.scard(LikeKey.LIKE_KEY.getPrefix() + articleId);
        List<Article> articleList = articleService.selectAllArtilce();
        for(Article article : articleList){
            int articleId = article.getArticleId();
            // 获取到点赞的Redis的key
            String likeKey = LikeKey.LIKE_KEY.getPrefix() + articleId;
            long likeCount = jedisService.scard(likeKey);
            article.setArticleLikeCount((int) likeCount);
            articleService.updateArticle(article);
        }
        log.info("每隔一小时将Redis的点赞数量更新至DB中");

    }


}
