package com.sharp.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.main.entity.PrivateMessage;
import com.sharp.main.entity.User;
import com.sharp.main.repository.MessageRepository;
import com.sharp.main.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public PrivateMessage addMessage(PrivateMessage message) {
		
//		PrivateMessage mess = new PrivateMessage();
//		
//		mess.setMessage(message.getMessage());
//		mess.setUser(message.getUser());
//		
		PrivateMessage storedmsg = messageRepository.save(message);
				
		return storedmsg;
	}

	@Override
	public List<PrivateMessage> getMessage(User user) {
		
		Optional<List<PrivateMessage>> message = messageRepository.findByUser(user);
		
		if(message.isPresent()) {
			return message.get();
		}
		
		return null;
	}
	
	@Override
	public PrivateMessage findMessageById(Integer messageId) {
		
		Optional<PrivateMessage> message = messageRepository.findById(messageId);
		
		if(message.isPresent()) {
			return message.get();
		}
		
		return null;
	}


	@Override
	public boolean deleteMessage(PrivateMessage message) {
		
		try {
		
			messageRepository.delete(message);
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}

	

}
