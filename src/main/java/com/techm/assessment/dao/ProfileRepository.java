package com.techm.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techm.assessment.model.UserProfile;

public interface ProfileRepository extends JpaRepository<UserProfile, Long>{

}
