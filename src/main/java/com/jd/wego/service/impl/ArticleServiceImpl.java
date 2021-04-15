package com.jd.wego.service.impl;

import com.jd.wego.dao.ArticleDao;
import com.jd.wego.entity.Article;
import com.jd.wego.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/7 15:55
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired(required = false)
    private ArticleDao articleDao;

    @Override
    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteArticle(int articleId) {
        articleDao.deleteArticle(articleId);
    }

    @Override
    public List<Article> selectArticleByCategoryId(int categoryId) {
        return articleDao.selectArticleByCategoryId(categoryId);
    }

    @Override
    public List<Article> selectArticleByLikeCount() {
        return articleDao.selectArticleByLikeCount();
    }

    @Override
    public List<Article> selectArticleByCommentCount() {
        return articleDao.selectArticleByCommentCount();
    }

    @Override
    public List<Article> selectArticleByViewCount() {
        return articleDao.selectArticleByViewCount();
    }

    @Override
    public List<Article> selectArticleBySchool(String userId) {
        return articleDao.selectArticleBySchool(userId);
    }

    @Override
    public List<Article> selectArticleByKeyword(String keyword) {
        return articleDao.selectArticleByKeyword(keyword);
    }

    @Override
    public Article selectArticleByTwoUserId(int articleId) {
        return articleDao.selectArticleByTwoUserId(articleId);
    }

    @Override
    public List<Article> selectAllArtilce() {
        return articleDao.selectAllArticle();
    }
}
