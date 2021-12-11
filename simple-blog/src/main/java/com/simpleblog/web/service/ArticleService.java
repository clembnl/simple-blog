package com.simpleblog.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.web.model.Article;
import com.simpleblog.web.repository.ArticleRepository;

import lombok.Data;

@Data
@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public Optional<Article> getArticle(final Long id) {
		return articleRepository.findById(id);
	}
	
	public Iterable<Article> getArticles() {
		return articleRepository.findAll();
	}
	
	public void deleteArticle(final Long id) {
		articleRepository.deleteById(id);
	}
	
	public Article saveArticle(Article article) {
		Article savedArticle = articleRepository.save(article);
		return savedArticle;
	}

}