package com.simpleblog.web.controller;

import java.sql.Date;
import java.util.Collection;
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
import com.simpleblog.web.model.Commentaire;
import com.simpleblog.web.model.Utilisateur;
import com.simpleblog.web.service.ArticleService;
import com.simpleblog.web.service.CommentaireService;
import com.simpleblog.web.service.UtilisateurService;

@RestController
public class CommentaireController {
	
	
	private CommentaireService commentaireService;
	private UtilisateurService utilisateurService;
	private ArticleService articleService;
	
	@Autowired
	public CommentaireController(CommentaireService commentaireService, UtilisateurService utilisateurService, 
								 ArticleService articleService) {
		this.commentaireService = commentaireService;
		this.utilisateurService = utilisateurService;
		this.articleService = articleService;
	}
	
	
	@PostMapping("/commentaire")
	public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurService.getUtilisateur(commentaire.getUtilisateur().getId());
		Optional<Article> optionalArticle = articleService.getArticle(commentaire.getArticle().getId());
		commentaire.setUtilisateur(optionalUtilisateur.get());
		commentaire.setArticle(optionalArticle.get());
		return commentaireService.saveCommentaire(commentaire);
	}
	
	@GetMapping("/commentaire/{id}")
	public Commentaire getCommentaire(@PathVariable("id") final Long id) {
		Optional<Commentaire> commentaire = commentaireService.getCommentaire(id);
		if(commentaire.isPresent()) {
			return commentaire.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/commentaires")
	public Iterable<Commentaire> getCommentaires() {
		return commentaireService.getCommentaires();
	}
	
	@GetMapping("/commentaires/{article_id}")
	public Collection<Commentaire> getCommentairesByArticle(@PathVariable("article_id") final Long articleId) {
		return commentaireService.getCommentairesByArticle(articleId);
	}
	
	@PutMapping("/commentaire/{id}")
	public Commentaire updateCommentaire(@PathVariable("id") final Long id, @RequestBody Commentaire commentaire) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurService.getUtilisateur(commentaire.getUtilisateur().getId());
		Optional<Article> optionalArticle = articleService.getArticle(commentaire.getArticle().getId());
		Optional<Commentaire> e = commentaireService.getCommentaire(id);
		if(e.isPresent()) {
			Commentaire currentCommentaire = e.get();
			
			commentaire.setUtilisateur(optionalUtilisateur.get());
			Date date = commentaire.getDate();
			if(date != null) {
				currentCommentaire.setDate(date);
			}
			String contenu = commentaire.getContenu();
			if(contenu != null) {
				currentCommentaire.setContenu(contenu);;
			}
			commentaire.setArticle(optionalArticle.get());
			commentaireService.saveCommentaire(currentCommentaire);
			return currentCommentaire;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/commentaire/{id}")
	public void deleteCommentaire(@PathVariable("id") final Long id) {
		commentaireService.deleteCommentaire(id);
	}

}
