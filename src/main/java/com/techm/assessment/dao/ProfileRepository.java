package com.techm.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techm.assessment.model.UserProfile;
import com.techm.assessment.model.UsersMonthWiseReport;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
	@Query("SELECT "
			+ "    new com.techm.assessment.model.UsersMonthWiseReport(to_char(v.createdAt,'Mon'), COUNT(v.id)) "
			+ "FROM " + "    UserProfile v " + "GROUP BY " + "    1")
	List<UsersMonthWiseReport> findMonthWiseReport();
}
