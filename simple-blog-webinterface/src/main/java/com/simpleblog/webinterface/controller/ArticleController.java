package com.simpleblog.webinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simpleblog.webinterface.model.Article;
import com.simpleblog.webinterface.service.ArticleService;

import lombok.Data;

@Data
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService service;
	
	@GetMapping("/")
	public String index(Model model) {
		Iterable<Article> listArticle = service.getArticles();
		model.addAttribute("articles", listArticle);
		return "index";
	}
	
	@GetMapping("/createArticle")
	public String createArticle(Model model) {
		Article e = new Article();
		model.addAttribute("article", e);
		return "formNewArticle";
	}
	
	@GetMapping("/updateArticle/{id}")
	public String updateArticle(@PathVariable("id") final int id, Model model) {
		Article e = service.getArticle(id);		
		model.addAttribute("article", e);	
		return "formUpdateArticle";		
	}
	
	@GetMapping("/deleteArticle/{id}")
	public ModelAndView deleteArticle(@PathVariable("id") final int id) {
		service.deleteArticle(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveArticle")
	public ModelAndView saveArticle(@ModelAttribute Article article) {
		service.saveArticle(article);
		return new ModelAndView("redirect:/");	
	}

}
