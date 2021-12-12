package com.simpleblog.webinterface.model;

import lombok.Data;

@Data
public class Utilisateur {
	
	private Long id;
	
	private String login;
	
	private Long type;
}
