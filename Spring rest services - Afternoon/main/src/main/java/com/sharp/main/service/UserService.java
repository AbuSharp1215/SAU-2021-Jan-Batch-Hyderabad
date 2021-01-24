package com.sharp.main.service;

import com.sharp.main.entity.User;

public interface UserService {
	public User addUser(User user);
	
	public User getUserById(Integer id);
	
	public User updateUser(User user);
	
	public User updateEmail(User user);
	
}
