package com.simpleblog.webinterface.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Article {
	
	private Long id;
	
	private String titre;
	
	private Long utilisateur;
	
	private Date date;
	
	private String contenu;
}
