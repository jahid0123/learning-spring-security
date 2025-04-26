package com.jmjbrothers.learn_spring_security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmjbrothers.learn_spring_security.model.User;
import com.jmjbrothers.learn_spring_security.repository.UserRepository;
import com.jmjbrothers.learn_spring_security.request.SigninRequest;
import com.jmjbrothers.learn_spring_security.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	
	
	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService,
			AuthenticationManager authenticationManager) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	
	@GetMapping("/public")
	public String publicEndPoint() {
		
		return "This is a public endpoint accessible to all.";
	}
	
	
	@GetMapping("/restricted")
	public String restrictedEndPoint() {
		
		return "This is a restricted endpoint accessible only to authenticated users.";
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user){
		
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: "+e.getMessage());
		}
		
	}
	
	@PostMapping("/signin")
public ResponseEntity<String> signIn(@RequestBody SigninRequest signinRequest){
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return ResponseEntity.ok("User authenticated successfully");
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: "+e.getMessage());
		}
		
	}

}
