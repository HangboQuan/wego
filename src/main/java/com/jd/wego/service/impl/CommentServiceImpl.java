package com.jd.wego.service.impl;

import com.jd.wego.dao.CommentDao;
import com.jd.wego.entity.Comment;
import com.jd.wego.service.CommentService;
import com.jd.wego.vo.CommentUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/17 17:35
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    CommentDao commentDao;

    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

    @Override
    public Comment selectCommentById(int commentId) {
        return commentDao.selectCommentById(commentId);
    }

    @Override
    public List<Comment> selectAllComment(int commentArticleId) {
        return commentDao.selectAllComment(commentArticleId);
    }

    @Override
    public int selectLastInsertCommentId() {
        return commentDao.selectLastInsertCommentId();
    }

    @Override
    public List<CommentUserVo> selectCommentLists(int commentArticleId) {
        return commentDao.selectCommentLists(commentArticleId);
    }
}
