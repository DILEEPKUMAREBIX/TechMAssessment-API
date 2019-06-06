package com.techm.assessment.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.assessment.model.Loginuser;
import com.techm.assessment.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
@ComponentScan(basePackages = { "com.techm.assessment" })
public class LoginController {

	
	@Autowired
	LoginService loginService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/register")
	public Loginuser createUser(@Valid @RequestBody Loginuser user) {
		Optional<Loginuser> loginUser = loginService.checkUserName(user);
		if (!loginUser.isPresent()) {
			user.setPassword(encoder.encode(user.getPassword()));
			return loginService.createUser(user);
		} else {
			throw new RuntimeException("User name already exists. Please try different user name");
		}
	}

	@PostMapping("/login")
	public Loginuser validateUser(@Valid @RequestBody Loginuser user) {
	      
		Loginuser existingUser = null;
		Optional<Loginuser> loginUser = null;
		loginUser = loginService.findUserName(user);
		if (loginUser.isPresent()) {
			existingUser = loginUser.get();
			if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
				return existingUser;
			} else {
				throw new RuntimeException("Invalid credintials provided");
			}
		} else {
			throw new RuntimeException("User not registered. Please register if you don't have account");
		}
	}
}