package com.sharp.main.service.impl;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sharp.main.entity.User;
import com.sharp.main.repository.UserRepository;
import com.sharp.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		//Encrypt the password
		String plainPassword = user.getPassword();
		String encryptedPassword = getEncryptedPassword(plainPassword);
		user.setPassword(encryptedPassword);
		
		//Add to DB
		User savedUser = userRepository.save(user);
		
		savedUser.setPassword(plainPassword);
		
		return savedUser;
	}
	

	private String getEncryptedPassword(String plainPassword) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10,new SecureRandom());
		
		String encodedPassword = encoder.encode(plainPassword);
		
		return encodedPassword;
	}


	@Override
	public User getUserById(Integer id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		return null;
	}


	@Override
	public User updateUser(User user) {
	
		String plainPassword = user.getPassword();
		String encryptedPassword = getEncryptedPassword(plainPassword);
		
		user.setPassword(encryptedPassword);
		
		User savedUser = userRepository.save(user);
		savedUser.setPassword(plainPassword);
		
		return savedUser;
	}


	@Override
	public User updateEmail(User user) {
		return userRepository.save(user);
	}


}
