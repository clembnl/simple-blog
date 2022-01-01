package com.simpleblog.webinterface.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.simpleblog.webinterface.auth.MyUserDetailsService;
import com.simpleblog.webinterface.model.UserDto;

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
	
	@GetMapping("/user/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	@PostMapping("/user/registration")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, 
			HttpServletRequest request,
			Errors errors) {
	    
		userService.registerNewUserAccount(userDto);
		return new ModelAndView("redirect:/");
	}

}
