package com.techm.assessment.service;

import java.util.List;

import com.techm.assessment.model.UserProfile;
import com.techm.assessment.model.UsersMonthWiseReport;

public interface ProfileService {
	
	List<UserProfile> getAllUsers();
	UserProfile createUser(UserProfile user);
	UserProfile getUserById( Long id);
	void deleteUser(UserProfile user);
	List<UsersMonthWiseReport> findMonthWiseReport();
}
