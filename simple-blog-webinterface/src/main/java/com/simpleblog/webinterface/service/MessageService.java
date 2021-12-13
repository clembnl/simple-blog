package com.simpleblog.webinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.model.Message;
import com.simpleblog.webinterface.repository.MessageProxy;

import lombok.Data;

@Data
@Service
public class MessageService {
	
	@Autowired
	private MessageProxy messageProxy;
	
	/*
	public Message getMessage(final int id) {
		return messageProxy.getMessage(id);
	}
	*/
	
	public Iterable<Message> getMessages() {
		return messageProxy.getMessages();
	}
	
	public Message saveMessage(Message message) {
		Message savedMessage;
		savedMessage = messageProxy.createMessage(message);
		return savedMessage;
	}

}
