package com.simpleblog.webinterface.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simpleblog.webinterface.model.User;
import com.simpleblog.webinterface.model.UserDto;
import com.simpleblog.webinterface.repository.UserProxy;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserProxy userProxy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userProxy.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUser(user.get());
	}
	
	public User registerNewUserAccount(UserDto userDto) {
		User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole("ROLE_USER");
        
        return userProxy.createUser(user);
	}

}
