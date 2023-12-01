package com.example.ChaltLog.Server.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="ChatLogs")
public class ChatLogEntry {
    
	@Id
	private String id;
	private String userId;
	private String message;
	
	@NotNull
    private Long timestamp;
    private Boolean isSent;
	
    
    
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Boolean getIsSent() {
		return isSent;
	}
	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}
	public ChatLogEntry(String userId,String id,String message, Long timestamp, Boolean isSent) {
		super();
		this.id=id;
		this.userId=userId;
		this.message = message;
		this.timestamp = timestamp;
		this.isSent = isSent;
	}
	public ChatLogEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
