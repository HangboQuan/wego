package com.jd.wego.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/6 14:39
 */
@Data
@Document(indexName = "wego", type = "_doc", createIndex = false)
public class Article {


    @Id
    @Field(type = FieldType.Integer, name = "article_id")
    private int articleId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", name = "article_title")
    private String articleTitle;

    @Field(type = FieldType.Text, name = "article_summary")
    private String articleSummary;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", name = "article_context")
    private String articleContent;

    @Field(type = FieldType.Integer, name = "article_view_count")
    private int articleViewCount;

    @Field(type = FieldType.Integer, name = "article_like_count")
    private int articleLikeCount;

    @Field(type = FieldType.Integer, name = "article_comment_count")
    private int articleCommentCount;

    /*@Field(type = FieldType.Date, name = "created_time", format = DateFormat.custom,
        pattern = "yyyy-MM-dd HH:mm:ss||yyyy-mm-dd||epoch_millis")*/
    @Field(type = FieldType.Date, name = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

//    @Field(type = FieldType.Date, name = "update_time", format = DateFormat.custom,
//        pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @Field(type = FieldType.Date, name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * isDelete表示两种状态，0表示未删除，1表示已删除
     */
    @Field(type = FieldType.Integer, name = "is_deleted")
    private int isDeleted;

    @Field(type = FieldType.Integer, name = "article_category_id")
    private int articleCategoryId;

    @Field(type = FieldType.Text, name = "article_user_id")
    private String articleUserId;
}
