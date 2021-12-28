package com.simpleblog.webinterface.repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.simpleblog.webinterface.CustomProperties;
import com.simpleblog.webinterface.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserProxy {
	
	@Autowired
	private CustomProperties props;
	
	public Iterable<User> getUsers() {

		String baseApiUrl = props.getApiUrl();
		String getUsersUrl = baseApiUrl + "/users";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<User>> response = restTemplate.exchange(
				getUsersUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<User>>() {}
			);
		
		log.debug("Get Users call " + response.getStatusCode().toString());
		
		return response.getBody();
	}	
	
	public User createUser(User e) {
		String baseApiUrl = props.getApiUrl();
		String createUserUrl = baseApiUrl + "/user";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> request = new HttpEntity<User>(e);
		ResponseEntity<User> response = restTemplate.exchange(
				createUserUrl, 
				HttpMethod.POST, 
				request, 
				User.class);
		
		log.debug("Create User call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Optional<User> findUserByUsername(String username) {
		return StreamSupport.stream(getUsers().spliterator(), false)
				.filter(user -> username.equals(user.getUsername()))
				.findFirst();
	}

}
