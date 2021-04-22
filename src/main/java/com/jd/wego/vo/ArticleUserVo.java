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

    private String userId;

    private String nickname;

    private String password;

    private String salt;

    private String avatar;

    private int achieveValue;

    private String school;

    private String loginIp;

    private Date createTime;

    private String loginType;
}
