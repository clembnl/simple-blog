package com.simpleblog.webinterface.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simpleblog.webinterface.model.Article;
import com.simpleblog.webinterface.model.Commentaire;
import com.simpleblog.webinterface.service.ArticleService;
import com.simpleblog.webinterface.service.CommentaireService;

import lombok.Data;

@Data
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService service;
	private CommentaireService comService;
	
	@GetMapping("/")
	public String index(Model model) {
		Iterable<Article> listArticle = service.getArticles();
		//Iterable<Commentaire> listCommentaire = comService.getCommentaires();
		model.addAttribute("articles", listArticle);
		//model.addAttribute("commentaires", listCommentaire);
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
		//article.setUtilisateur((long) 1);
		//java.util.Date utilDate = new java.util.Date();
		//java.sql.Date sqlDate = new Date(utilDate.getTime());
		//article.setDate(sqlDate);
		service.saveArticle(article);
		return new ModelAndView("redirect:/");	
	}

}
