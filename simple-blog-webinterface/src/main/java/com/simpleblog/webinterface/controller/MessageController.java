package com.simpleblog.webinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simpleblog.webinterface.model.Message;
import com.simpleblog.webinterface.service.MessageService;

import lombok.Data;

@Data
@Controller
public class MessageController {
	
	
	@Autowired
	private MessageService service;
	
	@GetMapping("/messages")
	public String index(Model model) {
		Iterable<Message> listMessage = service.getMessages();
		model.addAttribute("messages", listMessage);
		return "mailbox";
	}
	
	@GetMapping("/createMessage")
	public String createMessage(Model model) {
		Message e = new Message();
		model.addAttribute("message", e);
		return "formNewMessage";
	}
	
	@PostMapping("/saveMessage")
	public ModelAndView saveMessage(@ModelAttribute Message message) {
		service.saveMessage(message);
		return new ModelAndView("redirect:/");	
	}

}
