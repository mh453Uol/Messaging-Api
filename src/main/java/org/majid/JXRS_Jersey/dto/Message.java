package org.majid.JXRS_Jersey.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize 
public class Message {
	private UUID id;
	private String author;
	private String message;
	private LocalDateTime created;
	private HashMap<UUID, Comment> comments;
	
	public Message() {
		this.id = UUID.randomUUID();
		this.created = LocalDateTime.now();
		this.comments = new HashMap<UUID,Comment>();
	}
	
	public Message(String author, String message) {
		this();
		this.author = author;
		this.message = message;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	//Ignore in both json and xml
    @XmlTransient
	public HashMap<UUID, Comment> getComments() {
		return comments;
	}
	
    public void setComments(HashMap<UUID, Comment> comments) {
    	this.comments = comments;
    }
}
