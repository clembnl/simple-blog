package com.simpleblog.webinterface.model;

import java.sql.Date;
import java.util.Set;

import lombok.Data;

@Data
public class Article {
	
	private Long id;
	
	private String titre;
	
	private Date date;
	
	private String contenu;
	
	private Set<Commentaire> commentaires;
	
	public void addCommentaire(Commentaire commentaire) {
		this.commentaires.add(commentaire);
	}
}
