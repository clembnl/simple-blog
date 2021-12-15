package com.simpleblog.webinterface.controller;

import javax.annotation.Resource;

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
public class CommentaireController {
	
	@Resource
	private CommentaireService service;
	
	@Resource
	private ArticleService artService;
	
	@GetMapping("/createCommentaire/{article_id}")
	public String createCommentaire(Model model, @PathVariable("article_id") final int articleId) {
		Commentaire e = new Commentaire();
		//Article a = artService.getArticle(articleId);
		e.setArticle(artService.getArticle(articleId));
		//a.addCommentaire(e);
		model.addAttribute("commentaire", e);
		return "formNewCommentaire";
	}
	
	@PostMapping("/saveCommentaire/{article_id}")
	public ModelAndView saveCommentaire(@ModelAttribute Commentaire commentaire, 
										@PathVariable("article_id") final int articleId) {
		service.saveCommentaire(commentaire, articleId);
		return new ModelAndView("redirect:/");
		
	}

}
