package com.jd.wego.service;

import com.jd.wego.entity.Comment;
import com.jd.wego.vo.CommentUserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/17 17:34
 */
public interface CommentService {


    void insertComment(Comment comment);

    void deleteComment(int commentId);

    Comment selectCommentById(int commentId);

    List<Comment> selectAllComment(int commentArticleId);

    int selectLastInsertCommentId();

    List<CommentUserVo> selectCommentLists(int articleCommentId);

}
