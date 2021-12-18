package com.simpleblog.webinterface.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simpleblog.webinterface.model.Article;
import com.simpleblog.webinterface.service.ArticleService;

@Controller
public class LoginController {
	
	@Autowired
	private ArticleService service;
	
	@GetMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin(Model model) {
		Iterable<Article> listArticle = service.getArticles();
		model.addAttribute("articles", listArticle);
		return "admin";
	}

}
