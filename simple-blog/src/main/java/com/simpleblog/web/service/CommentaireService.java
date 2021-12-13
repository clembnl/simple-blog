package com.simpleblog.web.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.web.model.Commentaire;
import com.simpleblog.web.repository.CommentaireRepository;

import lombok.Data;

@Data
@Service
public class CommentaireService {
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	public Optional<Commentaire> getCommentaire(final Long id) {
		return commentaireRepository.findById(id);
	}
	
	public Iterable<Commentaire> getCommentaires() {
		return commentaireRepository.findAll();
	}
	
	public Collection<Commentaire> getCommentairesByArticle(final Long articleId) {
		return commentaireRepository.findAllCommentaireFromArticle(articleId);
	}
	
	public void deleteCommentaire(final Long id) {
		commentaireRepository.deleteById(id);
	}
	
	public Commentaire saveCommentaire(Commentaire commentaire) {
		Commentaire savedCommentaire = commentaireRepository.save(commentaire);
		return savedCommentaire;
	}

}