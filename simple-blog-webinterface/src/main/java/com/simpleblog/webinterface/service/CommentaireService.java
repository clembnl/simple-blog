package com.simpleblog.webinterface.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.model.Commentaire;
import com.simpleblog.webinterface.repository.CommentaireProxy;

import lombok.Data;

@Data
@Service
public class CommentaireService {
	
	@Autowired
	private CommentaireProxy commentaireProxy;
	
	public Commentaire getCommentaire(final int id) {
		return commentaireProxy.getCommentaire(id);
	}
	
	public Collection<Commentaire> getCommentairesByArticle(final int articleId) {
		return commentaireProxy.getCommentairesByArticle(articleId);
	}
	
	public Iterable<Commentaire> getCommentaires() {
		return commentaireProxy.getCommentaires();
	}
	
	public void deleteCommentaire(final int id) {
		commentaireProxy.deleteCommentaire(id);
	}
	
	public Commentaire saveCommentaire(Commentaire commentaire, final int articleId) {
		Commentaire savedCommentaire;

		if(commentaire.getId() == null) {
			// If id is null, then it is a new commentaire.
			savedCommentaire = commentaireProxy.createCommentaire(commentaire, articleId);
		} else {
			savedCommentaire = commentaireProxy.updateCommentaire(commentaire);
		}
		
		return savedCommentaire;
	}

}
