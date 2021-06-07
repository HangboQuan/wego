package com.jd.wego.redis;

/**
 * @author hbquan
 * @date 2021/4/7 16:57
 */
public class UserTokenKey extends BasePrefix {

    /**
     * 设置token的有效期为60天
     */
    public static final int TOKEN_EXPIRE = 3600 * 24 * 60;

    public UserTokenKey(String prefix) {
        super(prefix);
    }

    public UserTokenKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserTokenKey userTokenKey = new UserTokenKey(TOKEN_EXPIRE, "tk");
}
