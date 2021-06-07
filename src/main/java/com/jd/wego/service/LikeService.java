package com.jd.wego.service;

import java.util.*;


/**
 * @author hbquan
 * @date 2021/4/15 20:41
 */
public interface LikeService {

    /**
     * 将点赞的人添加进set集合，返回当前的点赞数
     *
     * @param key
     * @param value
     * @return
     */
    long like(String key, String value);

    /**
     * 将点赞的人从set集合中删除，返回当前的点赞数
     *
     * @param key
     * @param value
     * @return
     */
    long dislike(String key, String value);

    /**
     * 返回当前的点赞数
     *
     * @param key
     * @return
     */
    long likeCount(String key);

    Set<String> likeCountUserId(String key);

    void transLikedCountFromRedis2DB();


}
