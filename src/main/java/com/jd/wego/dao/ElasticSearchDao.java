package com.jd.wego.dao;

import com.jd.wego.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hbquan
 * @date 2021/4/20 11:13
 */
public interface ElasticSearchDao extends ElasticsearchRepository<Article, Integer> {
}
