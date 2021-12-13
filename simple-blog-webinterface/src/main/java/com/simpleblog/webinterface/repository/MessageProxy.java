package com.simpleblog.webinterface.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.simpleblog.webinterface.CustomProperties;
import com.simpleblog.webinterface.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageProxy {
	
	@Autowired
	private CustomProperties props;
	
	public Iterable<Message> getMessages() {

		String baseApiUrl = props.getApiUrl();
		String getMessagesUrl = baseApiUrl + "/messages";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Message>> response = restTemplate.exchange(
				getMessagesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Message>>() {}
			);
		
		log.debug("Get Messages call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/*
	public Message getMessage(int id) {
		String baseApiUrl = props.getApiUrl();
		String getMessageUrl = baseApiUrl + "/message/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Message> response = restTemplate.exchange(
				getMessageUrl, 
				HttpMethod.GET, 
				null,
				Message.class
			);
		
		log.debug("Get Message call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	*/
	
	public Message createMessage(Message e) {
		String baseApiUrl = props.getApiUrl();
		String createMessageUrl = baseApiUrl + "/message";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Message> request = new HttpEntity<Message>(e);
		ResponseEntity<Message> response = restTemplate.exchange(
				createMessageUrl, 
				HttpMethod.POST, 
				request, 
				Message.class);
		
		log.debug("Create Message call " + response.getStatusCode().toString());
		
		return response.getBody();
	}	

}
