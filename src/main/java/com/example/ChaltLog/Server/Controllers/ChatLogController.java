package com.example.ChaltLog.Server.Controllers;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChaltLog.Server.Entity.ChatLogEntry;
import com.example.ChaltLog.Server.Entity.User;
import com.example.ChaltLog.Server.Service.ChatLogService;
import com.example.ChaltLog.Server.Service.UserService;

//ChatLogController.java
@RestController
@RequestMapping("/v1/chatlogs")
public class ChatLogController {
	
	@Autowired
	ChatLogService chatService;
	
	@Autowired
	UserService userService;

 @PostMapping("/{user}")
 public ResponseEntity<String> createChatLog(@PathVariable String user, @RequestBody ChatLogEntry chatLogEntry) {
	 
	 String id = UUID.randomUUID().toString();
	 ChatLogEntry c=new ChatLogEntry(user,id,chatLogEntry.getMessage(),chatLogEntry.getTimestamp(),chatLogEntry.getIsSent());
	 
	 chatService.createChatLogEntry(c);
	 
     return ResponseEntity.status(HttpStatus.CREATED).body(id);
 }
 
 @GetMapping("/{user}")
 public ResponseEntity<List<ChatLogEntry>> getChatLogs(
         @PathVariable String user,
         @RequestParam(defaultValue = "10") int limit,
         @RequestParam(required = false) String start) {

	 List<ChatLogEntry> list=chatService.getChatLogEntry(user,limit,start);
	 
	 return ResponseEntity.status(HttpStatus.OK).body(list);
 }

 @DeleteMapping("/{userId}")
 public ResponseEntity<String> deleteChatLogs(@PathVariable String userId) {
     
	 chatService.deleteChatLogs(userId);
	 return ResponseEntity.ok("Successfully Deleted!");
 }

 
 @DeleteMapping("/{userId}/{msgid}")
 public ResponseEntity<String> deleteChatLog(@PathVariable String userId, @PathVariable String msgid) {
	 
	 chatService.deleteChatLogEntry(userId,msgid);
	 return ResponseEntity.ok("Successfully Deleted!");
 }
}

