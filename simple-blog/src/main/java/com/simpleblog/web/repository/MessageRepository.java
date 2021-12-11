package com.simpleblog.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.simpleblog.web.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
