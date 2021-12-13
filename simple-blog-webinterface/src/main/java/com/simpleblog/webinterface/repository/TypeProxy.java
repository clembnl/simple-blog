package com.simpleblog.webinterface.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.simpleblog.webinterface.CustomProperties;
import com.simpleblog.webinterface.model.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TypeProxy {
	
	@Autowired
	private CustomProperties props;
	
	public Type getType(int id) {
		String baseApiUrl = props.getApiUrl();
		String getTypeUrl = baseApiUrl + "/type/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Type> response = restTemplate.exchange(
				getTypeUrl, 
				HttpMethod.GET, 
				null,
				Type.class
			);
		
		log.debug("Get Type call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
