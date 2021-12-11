package com.simpleblog.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.web.model.Type;
import com.simpleblog.web.repository.TypeRepository;

import lombok.Data;

@Data
@Service
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;
	
	public Optional<Type> getType(final Long id) {
		return typeRepository.findById(id);
	}
	
	public Iterable<Type> getTypes() {
		return typeRepository.findAll();
	}
	
	public void deleteType(final Long id) {
		typeRepository.deleteById(id);
	}
	
	public Type saveType(Type type) {
		Type savedType = typeRepository.save(type);
		return savedType;
	}

}