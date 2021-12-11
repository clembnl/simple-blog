package com.simpleblog.web.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.web.model.Article;
import com.simpleblog.web.service.ArticleService;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping("/article")
	public Article createArticle(@RequestBody Article article) {
		return articleService.saveArticle(article);
	}
	
	@GetMapping("/article/{id}")
	public Article getArticle(@PathVariable("id") final Long id) {
		Optional<Article> article = articleService.getArticle(id);
		if(article.isPresent()) {
			return article.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/articles")
	public Iterable<Article> getArticles() {
		return articleService.getArticles();
	}
	
	@PutMapping("/article/{id}")
	public Article updateArticle(@PathVariable("id") final Long id, @RequestBody Article article) {
		Optional<Article> e = articleService.getArticle(id);
		if(e.isPresent()) {
			Article currentArticle = e.get();
			
			String titre = article.getTitre();
			if(titre != null) {
				currentArticle.setTitre(titre);
			}
			Long utilisateur = article.getUtilisateur();
			if(utilisateur != null) {
				currentArticle.setUtilisateur(utilisateur);;
			}
			Date date = article.getDate();
			if(date != null) {
				currentArticle.setDate(date);
			}
			String contenu = article.getContenu();
			if(contenu != null) {
				currentArticle.setContenu(contenu);;
			}
			Long nombreVues = article.getNombreVues();
			if(nombreVues != null) {
				currentArticle.setNombreVues(nombreVues);;
			}
			Long commentaire = article.getCommentaire();
			if(commentaire != null) {
				currentArticle.setCommentaire(commentaire);;
			}
			articleService.saveArticle(currentArticle);
			return currentArticle;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/article/{id}")
	public void deleteArticle(@PathVariable("id") final Long id) {
		articleService.deleteArticle(id);
	}

}
