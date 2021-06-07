package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/21 9:13
 * <p>
 * 我的粉丝
 */
@Data
public class Fans {

    private int id;

    private String userId;

    private String fansId;

    private Date createdTime;
}
