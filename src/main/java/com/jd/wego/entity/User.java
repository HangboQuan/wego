package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/3/30 16:55
 */
@Data
public class User {

    /**
     * 这里可以看做是手机号
     */
    private String userId;

    private String nickname;

    private String password;

    private String salt;

    private String avatar;

    private int achieveValue;

    private String school;

    private String loginIp;

    private Date createTime;
}
