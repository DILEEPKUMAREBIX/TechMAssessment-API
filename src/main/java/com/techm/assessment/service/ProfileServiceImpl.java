package com.techm.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techm.assessment.ResourceNotFoundException;
import com.techm.assessment.dao.ProfileRepository;
import com.techm.assessment.model.UserProfile;
import com.techm.assessment.model.UsersMonthWiseReport;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public List<UserProfile> getAllUsers() {
		return profileRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	}

	@Override
	public UserProfile createUser(UserProfile user) {
		return profileRepository.save(user);
	}

	@Override
	public UserProfile getUserById(Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user profile", "id", id));
	}

	@Override
	public void deleteUser(UserProfile user) {
		profileRepository.delete(user);
	}

	@Override
	public List<UsersMonthWiseReport> findMonthWiseReport() {
		return profileRepository.findMonthWiseReport();
	}

}
