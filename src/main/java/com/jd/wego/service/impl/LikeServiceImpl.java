package com.jd.wego.service.impl;

import com.jd.wego.redis.JedisService;
import com.jd.wego.service.ArticleService;
import com.jd.wego.service.LikeService;
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
    public void transLikedCountFromRedis2DB(String key) {
        long likedCount = jedisService.scard(key);

    }
}
