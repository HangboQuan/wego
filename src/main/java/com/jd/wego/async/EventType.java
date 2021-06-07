package com.jd.wego.async;

/**
 * @author hbquan
 * @date 2021/4/14 14:46
 * 枚举类：标识事件的类型
 */

public enum EventType {

    LIKE(0),
    DISLIKE(1),
    COMMNET(2),
    FOLLOW(3),
    UNFOLLOW(4);

    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
