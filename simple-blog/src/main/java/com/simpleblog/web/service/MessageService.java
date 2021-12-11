package com.simpleblog.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.web.model.Message;
import com.simpleblog.web.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public Optional<Message> getMessage(final Long id) {
		return messageRepository.findById(id);
	}
	
	public Iterable<Message> getMessages() {
		return messageRepository.findAll();
	}
	
	public void deleteMessage(final Long id) {
		messageRepository.deleteById(id);
	}
	
	public Message saveMessage(Message message) {
		Message savedMessage = messageRepository.save(message);
		return savedMessage;
	}

}