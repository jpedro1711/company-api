package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User where u.userName =:userName")
	User findByUsername(@Param("userName") String userName);
}
