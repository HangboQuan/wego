package com.jd.wego.entity;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/6 14:39
 */
public class Article {

    private int articleId;

    private String articleTitle;

    private String articleSummary;

    private String articleContent;

    private int articleViewCount;

    private int likeCount;

    private int commentCount;

    private Date createdTime;

    private Date updateTime;

    private int isDeleted;
}
