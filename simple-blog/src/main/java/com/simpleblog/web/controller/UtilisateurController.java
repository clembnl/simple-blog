package com.simpleblog.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.web.model.Type;
import com.simpleblog.web.model.Utilisateur;
import com.simpleblog.web.service.ArticleService;
import com.simpleblog.web.service.CommentaireService;
import com.simpleblog.web.service.MessageService;
import com.simpleblog.web.service.TypeService;
import com.simpleblog.web.service.UtilisateurService;

@RestController
public class UtilisateurController {
	
	
	private UtilisateurService utilisateurService;
	private TypeService typeService;
	private ArticleService articleService;
	private CommentaireService commentaireService;
	private MessageService messageService;
	
	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService, TypeService typeService,
								 ArticleService articleService, CommentaireService commentaireService,
								 MessageService messageService) {
		this.utilisateurService = utilisateurService;
		this.typeService = typeService;
		this.articleService = articleService;
		this.commentaireService = commentaireService;
		this.messageService = messageService;
	}
	
	@PostMapping("/utilisateur")
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Optional<Type> optionalType = typeService.getType(utilisateur.getType().getId());
		utilisateur.setType(optionalType.get());
		return utilisateurService.saveUtilisateur(utilisateur);
	}
	
	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") final Long id) {
		Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);
		if(utilisateur.isPresent()) {
			return utilisateur.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/utilisateurs")
	public Iterable<Utilisateur> getUtilisateurs() {
		return utilisateurService.getUtilisateurs();
	}
	
	@PutMapping("/utilisateur/{id}")
	public Utilisateur updateUtilisateur(@PathVariable("id") final Long id, @RequestBody Utilisateur utilisateur) {
		Optional<Type> optionalType = typeService.getType(utilisateur.getType().getId());
		Optional<Utilisateur> e = utilisateurService.getUtilisateur(id);
		if(e.isPresent()) {
			Utilisateur currentUtilisateur = e.get();
			
			String login = utilisateur.getLogin();
			if(login != null) {
				currentUtilisateur.setLogin(login);
			}
			currentUtilisateur.setType(optionalType.get());;
			utilisateurService.saveUtilisateur(currentUtilisateur);
			return currentUtilisateur;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/utilisateur/{id}")
	public void deleteUtilisateur(@PathVariable("id") final Long id) {
		utilisateurService.deleteUtilisateur(id);
	}

}
