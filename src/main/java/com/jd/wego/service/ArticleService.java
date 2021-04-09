package com.jd.wego.service;

import com.jd.wego.entity.Article;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/7 15:55
 */
public interface ArticleService {

    void insertArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    List<Article> selectArticleByCategoryId(int categoryId);

    List<Article> selectArticleByLikeCount();

    List<Article> selectArticleByCommentCount();

    List<Article> selectArticleByViewCount();

    List<Article> selectArticleBySchool(int userId);

}
