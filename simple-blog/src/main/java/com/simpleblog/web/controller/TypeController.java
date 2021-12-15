package com.simpleblog.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.web.model.Type;
import com.simpleblog.web.service.TypeService;
import com.simpleblog.web.service.UtilisateurService;

@RestController
public class TypeController {
	
	
	private TypeService typeService;
	private UtilisateurService utilisateurService;
	
	@Autowired
	public TypeController(TypeService typeService, UtilisateurService utilisateurService) {
		this.typeService = typeService;
		this.utilisateurService = utilisateurService;
	}
	
	@PostMapping("/type")
	public Type createType(@RequestBody Type type) {
		return typeService.saveType(type);
	}
	
	@GetMapping("/type/{id}")
	public Type getType(@PathVariable("id") final Long id) {
		Optional<Type> type = typeService.getType(id);
		if(type.isPresent()) {
			return type.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/types")
	public Iterable<Type> getTypes() {
		return typeService.getTypes();
	}
	
	@PutMapping("/type/{id}")
	public Type updateType(@PathVariable("id") final Long id, @RequestBody Type type) {
		Optional<Type> e = typeService.getType(id);
		if(e.isPresent()) {
			Type currentType = e.get();
			
			String typeName = type.getType();
			if(type != null) {
				currentType.setType(typeName);
			}
			String presentation = type.getPresentation();
			if(presentation != null) {
				currentType.setPresentation(presentation);;
			}
			typeService.saveType(currentType);
			return currentType;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/type/{id}")
	public void deleteType(@PathVariable("id") final Long id) {
		typeService.deleteType(id);
	}

}
