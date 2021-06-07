package com.jd.wego.redis;

/**
 * @author hbquan
 * @date 2021/4/14 15:38
 * 点赞的Rediskey
 */
public class LikeKey extends BasePrefix {

    public LikeKey(String prefix) {
        super(prefix);
    }

    public LikeKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 在这里的话，其实应该可以理解为这个key，他是作为一个消息队列来发送消息
     */
    public static LikeKey LIKE_ASYNC_KEY = new LikeKey("likeAsync");

    public static LikeKey LIKE_KEY = new LikeKey("like");
}
