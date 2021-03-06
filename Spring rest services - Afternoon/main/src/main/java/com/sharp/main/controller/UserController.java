package com.sharp.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.main.entity.User;
import com.sharp.main.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public User signUp(@RequestBody Map<String, String> userDetails) {
		
		System.out.println(userDetails);
		
		String emailId = userDetails.get("emailId");
		String password = userDetails.get("password");
		
		User user = new User();
		
		user.setEmailId(emailId);
		user.setPassword(password);
		
		//send it to service
		
		service.addUser(user);
		
		return user;
		
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody User userDetails
			,@PathVariable("userId") Integer userId) {
			
		User user = service.getUserById(userId);
		
		//check for user is found or not
		
		if(user == null) {
			return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
		}
		
		// if user is found then update the receive fields only
		User updatedUser = null;
		
		
		
		String emailId = userDetails.getEmailId();
		String password = userDetails.getPassword();
		
		if(emailId!=null && password!=null) {
			user.setEmailId(emailId);
			user.setPassword(password);
			updatedUser = service.updateUser(user);
		}
		
		else if(emailId!=null) {
			user.setEmailId(emailId);
			updatedUser = service.updateEmail(user);
			return new ResponseEntity<Object>(updatedUser.getEmailId()+" updated for userID "+userId,HttpStatus.OK);
		}
		
		else if(password!=null) {
			user.setPassword(password);
			updatedUser = service.updateUser(user);
		}
	
		return new ResponseEntity<Object>(updatedUser,HttpStatus.OK);
		
	}

}
