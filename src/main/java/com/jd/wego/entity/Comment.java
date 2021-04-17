package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/17 17:12
 */
@Data
public class Comment {

    private int commentId;

    private int commentArticleId;

    private String commentUserId;

    private String commentContent;

    private int commentLikeCount;

    private int commentCount;

    private Date commentCreatedTime;


}
