package com.techm.assessment.service;

import java.util.Optional;

import javax.validation.Valid;

import com.techm.assessment.model.Loginuser;

public interface LoginService {
	public Loginuser createUser(Loginuser user);

	// public Optional<Loginuser> validateUser(@Valid Loginuser user);

}
