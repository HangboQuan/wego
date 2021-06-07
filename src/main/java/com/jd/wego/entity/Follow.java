package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/21 9:14
 * <p>
 * 我的关注
 */
@Data
public class Follow {

    private int id;

    private String userId;

    private String followId;

    private Date createdTime;
}
