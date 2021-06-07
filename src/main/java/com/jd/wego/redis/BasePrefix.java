package com.jd.wego.redis;


/**
 * @author hbquan
 * @date 2021/3/30 20:01
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {

        // 获取该类的名字
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
