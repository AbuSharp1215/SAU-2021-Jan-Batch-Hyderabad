package com.sharp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharp.main.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
}
