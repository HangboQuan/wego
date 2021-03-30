package com.jd.wego.redis;

/**
 * @author hbquan
 * @date 2021/3/30 21:16
 */
public class VerifyCodeKey extends BasePrefix {

    public VerifyCodeKey(String prefix) {
        super(prefix);
    }

    public VerifyCodeKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static VerifyCodeKey verifyCodeKey = new VerifyCodeKey(180, "vc");
}
