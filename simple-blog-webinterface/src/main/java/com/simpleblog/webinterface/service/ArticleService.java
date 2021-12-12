package com.simpleblog.webinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.model.Article;
import com.simpleblog.webinterface.repository.ArticleProxy;

import lombok.Data;

@Data
@Service
public class ArticleService {
	
	@Autowired
	private ArticleProxy articleProxy;
	
	public Article getArticle(final int id) {
		return articleProxy.getArticle(id);
	}
	
	public Iterable<Article> getArticles() {
		return articleProxy.getArticles();
	}
	
	public void deleteArticle(final int id) {
		articleProxy.deleteArticle(id);
	}
	
	public Article saveArticle(Article article) {
		Article savedArticle;

		if(article.getId() == null) {
			// If id is null, then it is a new article.
			savedArticle = articleProxy.createArticle(article);
		} else {
			savedArticle = articleProxy.updateArticle(article);
		}
		
		return savedArticle;
	}

}
