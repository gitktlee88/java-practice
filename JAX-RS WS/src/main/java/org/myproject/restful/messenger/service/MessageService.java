package org.myproject.restful.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myproject.restful.messenger.database.DatabaseClass;
import org.myproject.restful.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		if (messages.size() <= 0) {
			messages.put(1L, new Message(1, "First message", "KTL1"));
			messages.put(2L, new Message(2, "Second message", "KTL2"));
		}
	}
	
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message, long id) {
		if (id <= 0 || id > messages.size()) {
			return null;
		}
		messages.put(id, message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
	
	
	
	
	
	
	
	
	

}
