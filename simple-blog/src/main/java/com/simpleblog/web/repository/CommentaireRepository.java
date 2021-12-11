package com.simpleblog.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.simpleblog.web.model.Commentaire;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{

}
