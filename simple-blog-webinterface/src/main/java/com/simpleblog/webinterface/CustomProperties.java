package com.simpleblog.webinterface;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.simpleblog.webinterface")
public class CustomProperties {
	
	private String apiUrl;
	
}
