package com.techm.assessment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.assessment.ResourceNotFoundException;
import com.techm.assessment.dao.LoginRepository;
import com.techm.assessment.model.Loginuser;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginRepository loginRepository;

	@Override
	public Loginuser createUser(Loginuser user) {
		return loginRepository.save(user);
	}

	@Override
	public Optional<Loginuser> findUserName(Loginuser user) {
		Optional<Loginuser> resultUser = loginRepository.findUserName(user.getEmail()); //.orElseThrow(() -> new ResourceNotFoundException("login", "username", user.getEmail()));
		return resultUser;
	}
	
	@Override
	public Optional<Loginuser> checkUserName(Loginuser user) {
		return loginRepository.findUserName(user.getEmail());
	}

	@Override
	public Loginuser findUser(String email, String password) {
		Loginuser loginUser = loginRepository.findUser(email, password).orElseThrow(() -> new ResourceNotFoundException("login", "username and password", email));
		return loginUser;
	}

}
