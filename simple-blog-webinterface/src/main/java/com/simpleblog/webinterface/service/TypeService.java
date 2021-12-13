package com.simpleblog.webinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.model.Type;
import com.simpleblog.webinterface.repository.TypeProxy;

import lombok.Data;

@Data
@Service
public class TypeService {
	
	@Autowired
	private TypeProxy typeProxy;
	
	public Type getType(final int id) {
		return typeProxy.getType(id);
	}

}
