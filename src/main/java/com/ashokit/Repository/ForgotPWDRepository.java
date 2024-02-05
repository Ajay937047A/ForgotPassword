package com.ashokit.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.UserEntity;

@Repository
public interface ForgotPWDRepository extends  JpaRepository<UserEntity, Serializable>{
	
	
	public UserEntity  findByUserEmail(String userEmail);
	
	

}
