package com.simpleblog.webinterface.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.simpleblog.webinterface.CustomProperties;
import com.simpleblog.webinterface.model.Commentaire;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommentaireProxy {
	
	@Autowired
	private CustomProperties props;
	
	
	public Iterable<Commentaire> getCommentaires() {

		String baseApiUrl = props.getApiUrl();
		String getCommentairesUrl = baseApiUrl + "/commentaires";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Commentaire>> response = restTemplate.exchange(
				getCommentairesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Commentaire>>() {}
			);
		
		log.debug("Get Commentaires call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	
	public Commentaire getCommentaire(int id) {
		String baseApiUrl = props.getApiUrl();
		String getCommentaireUrl = baseApiUrl + "/commentaire/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Commentaire> response = restTemplate.exchange(
				getCommentaireUrl, 
				HttpMethod.GET, 
				null,
				Commentaire.class
			);
		
		log.debug("Get Commentaire call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Collection<Commentaire> getCommentairesByArticle(int articleId) {

		String baseApiUrl = props.getApiUrl();
		String getCommentairesUrl = baseApiUrl + "/commentaires/" + articleId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Collection<Commentaire>> response = restTemplate.exchange(
				getCommentairesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Collection<Commentaire>>() {}
			);
		
		log.debug("Get Commentaires call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Commentaire createCommentaire(Commentaire e) {
		String baseApiUrl = props.getApiUrl();
		String createCommentaireUrl = baseApiUrl + "/commentaire";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Commentaire> request = new HttpEntity<Commentaire>(e);
		ResponseEntity<Commentaire> response = restTemplate.exchange(
				createCommentaireUrl, 
				HttpMethod.POST, 
				request, 
				Commentaire.class);
		
		log.debug("Create Commentaire call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Commentaire updateCommentaire(Commentaire e) {
		String baseApiUrl = props.getApiUrl();
		String updateCommentaireUrl = baseApiUrl + "/commentaire/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Commentaire> request = new HttpEntity<Commentaire>(e);
		ResponseEntity<Commentaire> response = restTemplate.exchange(
				updateCommentaireUrl, 
				HttpMethod.PUT, 
				request, 
				Commentaire.class);
		
		log.debug("Update Commentaire call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public void deleteCommentaire(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteCommentaireUrl = baseApiUrl + "/commentaire/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteCommentaireUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Commentaire call " + response.getStatusCode().toString());
	}

}
