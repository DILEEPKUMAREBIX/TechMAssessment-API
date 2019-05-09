package com.techm.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.assessment.dao.LoginRepository;
import com.techm.assessment.model.Loginuser;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginRepository loginRepository;
	
	public Loginuser createUser(Loginuser user) {
		return loginRepository.save(user);
	}
	
//	public Loginuser validateUser(Loginuser user) {
//		return loginRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("customers", "id", user.getId()));
//	}

}
