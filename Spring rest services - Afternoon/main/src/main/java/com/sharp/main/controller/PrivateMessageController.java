package com.sharp.main.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.main.entity.PrivateMessage;
import com.sharp.main.entity.User;
import com.sharp.main.service.MessageService;
import com.sharp.main.service.UserService;

@RestController
@RequestMapping("/api/message")
public class PrivateMessageController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<Object> postMessage(@RequestBody PrivateMessage message,
			@PathVariable("userId") Integer userId) {
		
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
		}
		
		message.setUser(user);
		message.setCreatedTime(Timestamp.from(Instant.now()));
		messageService.addMessage(message);
		
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<Object> getMessage(@PathVariable("userId") Integer userId) {
		
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
		}
		
//		message.setUser(user);
//		service.addMessage(message);
		
		List<PrivateMessage> message = messageService.getMessage(user);
		//System.out.println(message.isEmpty());
		if(message == null || message.isEmpty()) {
			return new ResponseEntity<Object>("No Messages Found",HttpStatus.NOT_FOUND);
		}
		
		
		return ResponseEntity.ok(message);
	}
	
	
	@DeleteMapping("/delete/{messageId}")
	public ResponseEntity<Object> deleteMessage(@PathVariable("messageId") Integer id){
		
		PrivateMessage message = messageService.findMessageById(id);
		
		if(message == null) {
			return new ResponseEntity<Object>("Message Not Found",HttpStatus.NOT_FOUND);
		}
		
		boolean isDeleted = messageService.deleteMessage(message);
		
		if(!isDeleted) {
			return new ResponseEntity<Object>("Message not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>("Message deleted successfully", HttpStatus.OK);
	}
}
