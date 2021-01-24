package com.sharp.main.service;

import java.util.List;

import com.sharp.main.entity.PrivateMessage;
import com.sharp.main.entity.User;


public interface MessageService {
	
	public PrivateMessage addMessage(PrivateMessage message);
	
	public List<PrivateMessage> getMessage(User user);
	
	public boolean deleteMessage(PrivateMessage message);
	
	public PrivateMessage findMessageById(Integer messageId);
}
