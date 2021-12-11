package com.simpleblog.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="articles")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titre;
	
	@Column(name="utilisateur_id")
	private Long utilisateur;
	
	private Date date;
	
	private String contenu;
	
	@Column(name="nombre_vues")
	private Long nombreVues;
}
