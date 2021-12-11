package com.simpleblog.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.simpleblog.web.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}
