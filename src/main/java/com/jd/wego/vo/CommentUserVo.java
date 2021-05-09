package com.jd.wego.vo;

import com.jd.wego.entity.Comment;
import lombok.Data;

/**
 * @author hbquan
 * @date 2021/5/9 13:30
 */
@Data
public class CommentUserVo extends Comment {

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;
}
