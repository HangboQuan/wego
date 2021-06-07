package com.jd.wego.redis;

/**
 * @author hbquan
 * @date 2021/4/21 9:51
 */
public class FollowKey extends BasePrefix {

    public FollowKey(String prefix) {
        super(prefix);
    }

    public FollowKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 我的关注
     */
    public static FollowKey followKey = new FollowKey("myFollow");
}
