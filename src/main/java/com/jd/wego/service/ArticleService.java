package com.jd.wego.service;

import com.jd.wego.entity.Article;
import com.jd.wego.vo.ArticleUserVo;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/7 15:55
 */
public interface ArticleService {

    void insertArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    Article selectArticleByArticleId(int articleId);

    List<Article> selectArticleByCategoryId(int categoryId);

    List<Article> selectArticleByLikeCount();

    List<Article> selectArticleByCommentCount();

    List<ArticleUserVo> selectArticleByViewCount();

    List<ArticleUserVo> selectArticleBySchool(String schoolName, int categoryId);

    List<ArticleUserVo> selectArticleByKeyword(String keyword);

    Article selectArticleByTwoUserId(int articleId);

    List<Article> selectAllArtilce();

    List<Article> selectAllArticleByES();

    List<ArticleUserVo> selectAllArticleIndexViewData();

    List<ArticleUserVo> selectAllArticleCategoryData(int categoryId);

    ArticleUserVo selectAllArticleDetail(int articleId);

    List<ArticleUserVo> selectArticleByKeywords(String keywords);


}
