package com.simpleblog.webinterface.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Message {
	
	private Long id;
	
	private Long utilisateur;
	
	private Date date;
	
	private String contenu;

}
