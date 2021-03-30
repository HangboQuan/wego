package com.jd.wego.redis;

/**
 * @author hbquan
 * @date 2021/3/30 18:59
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
