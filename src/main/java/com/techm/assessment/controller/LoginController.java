package com.techm.assessment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.assessment.ResourceNotFoundException;
import com.techm.assessment.dao.LoginRepository;
import com.techm.assessment.model.Loginuser;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
@ComponentScan(basePackages = { "com.techm.assessment" })
public class LoginController {
	
	@Autowired
	LoginRepository loginRepository;
	
	@PostMapping("/register")
	public Loginuser createUser(@Valid @RequestBody Loginuser user) {
		// return loginservice.createUser(user);
		return loginRepository.save(user);
	}
	
	@PostMapping("/login")
	public Loginuser validateUser(@Valid @RequestBody Loginuser user) {
		Loginuser loginUser = null;
		loginUser = loginRepository.findUserName(user.getUsername()).orElseThrow(() -> new ResourceNotFoundException("login", "username", user.getUsername()));
		if (!loginUser.equals(null)) {
			loginUser = loginRepository.findUser(user.getUsername(), user.getPassword()).orElseThrow(() -> new ResourceNotFoundException("login", "username and password", user.getUsername()));
		}
		
		return loginUser;
	}

}
