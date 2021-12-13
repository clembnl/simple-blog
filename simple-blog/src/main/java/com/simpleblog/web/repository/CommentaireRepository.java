package com.simpleblog.web.repository;


import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.simpleblog.web.model.Commentaire;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{
	
	@Query(
			  value = "SELECT * FROM commentaires c WHERE c.article_id = ?1", 
			  nativeQuery = true)
	Collection<Commentaire> findAllCommentaireFromArticle(Long article_id);
	

}
