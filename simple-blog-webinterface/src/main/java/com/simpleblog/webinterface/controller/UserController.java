package com.simpleblog.webinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.simpleblog.webinterface.auth.MyUserDetailsService;

import lombok.Data;

@Data
@Controller
public class UserController {
	
	@Autowired
	private MyUserDetailsService userService;
	
	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

}
