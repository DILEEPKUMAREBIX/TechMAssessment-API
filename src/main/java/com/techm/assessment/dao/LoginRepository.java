package com.techm.assessment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.assessment.model.Loginuser;

@Repository
public interface LoginRepository extends JpaRepository<Loginuser, Long> {
	
	@Query("SELECT r FROM Loginuser r where r.username = :username and r.password = :password" ) 
	Optional<Loginuser> findUser(String username, String password);
	
	@Query("SELECT r FROM Loginuser r where r.username = :username" ) 
	Optional<Loginuser> findUserName(String username);

}
