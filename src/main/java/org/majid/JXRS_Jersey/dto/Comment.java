package org.majid.JXRS_Jersey.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Comment {
	private UUID id;
	private String text;
	private LocalDateTime created;
	private String authorProfileName;
	
	
	public Comment() {
		this.created = LocalDateTime.now();
		this.id = UUID.randomUUID();
	}
	
	public Comment(String text, String authorProfileName) {
		this();
		this.text = text;
		this.authorProfileName = authorProfileName;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getAuthorProfileName() {
		return authorProfileName;
	}

	public void setAuthorProfileName(String authorProfileName) {
		this.authorProfileName = authorProfileName;
	} 
}
