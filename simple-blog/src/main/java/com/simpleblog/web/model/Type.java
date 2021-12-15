package com.simpleblog.web.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="types")
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String type;
	
	private String presentation;
	
	//@JsonManagedReference
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch = FetchType.EAGER)
	private Set<Utilisateur> utilisateurs;

}
