package com.simpleblog.webinterface.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Commentaire {

	private Long id;	
	
	private Long utilisateur;
	
	private Date date;
	
	private String contenu;
	
	private Long article;
}
