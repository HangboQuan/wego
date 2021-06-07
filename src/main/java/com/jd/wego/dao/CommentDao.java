package com.jd.wego.dao;

import com.jd.wego.entity.Comment;
import com.jd.wego.vo.CommentUserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/17 17:17
 */
@Mapper
public interface CommentDao {

    String TABLE_NAME = " comment ";

    String INSERT_VALUE = " comment_article_id, comment_user_id, comment_content," +
            "comment_like_count, comment_count, comment_created_time";

    String SELECT_VALUE = " comment_id, " + INSERT_VALUE;

    @Insert("insert into " + TABLE_NAME + "(" + INSERT_VALUE + ")values(#{commentArticleId}, #{commentUserId}," +
            "#{commentContent}, #{commentLikeCount}, #{commentCount}, #{commentCreatedTime})")
    void insertComment(Comment comment);

    @Delete("delete from " + TABLE_NAME + " where comment_id = #{commentId}")
    void deleteComment(int commentId);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME + " where comment_id = #{commentId}")
    Comment selectCommentById(int commentId);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME + "where comment_article_id = #{commentArticleId} " +
            "order by comment_created_time desc")
    List<Comment> selectAllComment(int commentArticleId);


    @Select("select max(comment_id) from " + TABLE_NAME)
    int selectLastInsertCommentId();


    @Select("select comment.*, user.avatar, user.nickname from comment inner join user where comment_article_id = #{commentArticleId} and user_id = comment_user_id " +
            " order by comment_created_time desc")
    List<CommentUserVo> selectCommentLists(int commentArticleId);
}
