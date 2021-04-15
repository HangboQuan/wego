package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/6 14:39
 */
@Data
public class Article {

    private int articleId;

    private String articleTitle;

    private String articleSummary;

    private String articleContent;

    private int articleViewCount;

    private int articleLikeCount;

    private int articleCommentCount;

    private Date createdTime;

    private Date updateTime;

    private int isDeleted;

    private int articleCategoryId;

    private String articleUserId;
}
