package com.techm.assessment.service;

import java.util.Optional;

import com.techm.assessment.model.Loginuser;

public interface LoginService {
	
	Loginuser createUser(Loginuser user);
	Loginuser findUser(String email, String password);
	Optional<Loginuser> findUserName(Loginuser user);
	Optional<Loginuser> checkUserName(Loginuser user);

}
