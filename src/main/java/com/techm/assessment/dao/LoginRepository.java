package com.techm.assessment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.assessment.model.Loginuser;

@Repository
public interface LoginRepository extends JpaRepository<Loginuser, Long> {

	@Query("SELECT r FROM Loginuser r where r.email = :email and r.password = :password")
	Optional<Loginuser> findUser(String email, String password);

	@Query("SELECT r FROM Loginuser r where r.email= :email")
	Optional<Loginuser> findUserName(String email);

}
