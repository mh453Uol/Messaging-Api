package org.majid.JXRS_Jersey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.majid.JXRS_Jersey.database.InMemoryDatabase;
import org.majid.JXRS_Jersey.dto.Message;

public class MessageService {
	
	private HashMap<UUID,Message> database = InMemoryDatabase.getMessages();
	
	
	public MessageService() {
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(this.database.values());
	};
	
	
	public Message getMessage(UUID key){
		return this.database.get(key);
	}
	
	public Message addMessage(Message message) {
		UUID key = UUID.randomUUID();
		message.setId(key);
		
		this.database.put(key, message);
		
		return message;
	}
	
	public Message updateMessage(Message message) {
		return this.database.replace(message.getId(), message);
	}
	
	public Message removeMessage(Message message) {
		return this.database.remove(message.getId());
	}

	public List<Message> filterByPagination(int offset, int amount, ArrayList<Message> messages) {
		return messages.stream().skip(offset).limit(amount).collect(Collectors.toList());
	}
	public ArrayList<Message> filterByYear(int year, ArrayList<Message> messages){
		ArrayList<Message> inRange = new ArrayList<Message>();
		
		for(Message message: messages) {
			if(message.getCreated().getYear() == year) {
				inRange.add(message);
			}
		}
		
		return inRange;
	}
}
