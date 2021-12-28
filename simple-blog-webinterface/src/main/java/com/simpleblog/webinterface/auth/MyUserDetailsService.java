package com.simpleblog.webinterface.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.repository.UserProxy;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserProxy userProxy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) userProxy
				.findUserByUsername(username)
				.orElseThrow(() -> 
					new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
