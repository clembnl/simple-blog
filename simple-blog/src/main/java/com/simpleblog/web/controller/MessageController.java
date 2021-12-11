package com.simpleblog.web.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.web.model.Message;
import com.simpleblog.web.service.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/message")
	public Message createMessage(@RequestBody Message message) {
		return messageService.saveMessage(message);
	}
	
	@GetMapping("/message/{id}")
	public Message getMessage(@PathVariable("id") final Long id) {
		Optional<Message> message = messageService.getMessage(id);
		if(message.isPresent()) {
			return message.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/messages")
	public Iterable<Message> getMessages() {
		return messageService.getMessages();
	}
	
	@PutMapping("/message/{id}")
	public Message updateMessage(@PathVariable("id") final Long id, @RequestBody Message message) {
		Optional<Message> e = messageService.getMessage(id);
		if(e.isPresent()) {
			Message currentMessage = e.get();
			
			Long utilisateur = message.getUtilisateur();
			if(utilisateur != null) {
				currentMessage.setUtilisateur(utilisateur);;
			}
			Date date = message.getDate();
			if(date != null) {
				currentMessage.setDate(date);
			}
			String contenu = message.getContenu();
			if(contenu != null) {
				currentMessage.setContenu(contenu);;
			}
			messageService.saveMessage(currentMessage);
			return currentMessage;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/message/{id}")
	public void deleteMessage(@PathVariable("id") final Long id) {
		messageService.deleteMessage(id);
	}

}
