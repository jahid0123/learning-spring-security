package com.jmjbrothers.learn_spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmjbrothers.learn_spring_security.model.User;
import com.jmjbrothers.learn_spring_security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public User findByUsername (String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
	
	

}
