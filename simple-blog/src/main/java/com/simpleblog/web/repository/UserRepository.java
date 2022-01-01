package com.simpleblog.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.simpleblog.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
