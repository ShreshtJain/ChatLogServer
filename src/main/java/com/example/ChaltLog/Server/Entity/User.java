package com.example.ChaltLog.Server.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="Users")
public class User {
	
	@Id
	@Size(max = 16)
	private String userId;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true,
			fetch = FetchType.EAGER)
    Set<ChatLogEntry> chats = new HashSet<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<ChatLogEntry> getChats() {
		return chats;
	}

	public void setChats(Set<ChatLogEntry> chats) {
		this.chats = chats;
	}

	public User(@Size(max = 16) String userId, Set<ChatLogEntry> chats) {
		super();
		this.userId = userId;
		this.chats = chats;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
}
