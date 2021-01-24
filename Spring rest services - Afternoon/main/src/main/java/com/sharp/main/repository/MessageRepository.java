package com.sharp.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharp.main.entity.PrivateMessage;
import com.sharp.main.entity.User;

public interface MessageRepository extends JpaRepository<PrivateMessage, Integer>{
	
	public Optional<List<PrivateMessage>> findByUser(User user);
	
}