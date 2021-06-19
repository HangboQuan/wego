package com.jd.wego.vo;

import com.jd.wego.entity.Article;
import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/21 16:23
 */
@Data
public class ArticleUserVo extends Article {


    private String nickname;

    private String avatar;

    /**
     * 0 表示点赞了， 1 表示未点赞
     */
    private int isLiked;

    @Override
    public String toString() {
        return "ArticleUserVo{" + super.toString() +
                "nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}
