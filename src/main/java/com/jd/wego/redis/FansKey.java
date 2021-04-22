package com.jd.wego.redis;


/**
 * @author hbquan
 * @date 2021/4/21 14:47
 */
public class FansKey extends BasePrefix {

    public FansKey(String prefix) {
        super(prefix);
    }

    public FansKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static FansKey fansKey = new FansKey("myFans");
}
