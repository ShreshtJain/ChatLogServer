package com.example.ChaltLog.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ChaltLog.Server.Entity.ChatLogEntry;
import com.example.ChaltLog.Server.Exceptions.ResourceNotFoundException;
import com.example.ChaltLog.Server.Repository.ChatLogRepository;
import com.example.ChaltLog.Server.Repository.UserRepository;

@Service
public class ChatLogService {
	

		
	    private final ChatLogRepository chatLogRepository;
	    private final UserRepository userRepository;
	    
	    @Autowired
	    public ChatLogService(ChatLogRepository chatLogRepository,UserRepository userRepository) {
	        this.chatLogRepository= chatLogRepository;
	        this.userRepository=userRepository;
	    }

	    private int getTotalEntry()
	    {
	    	return chatLogRepository.getTotalEntry();
	    }
	    
	    public List<ChatLogEntry> getChatLogEntry(String userId,int limit,String start) {
	    	
	    	
	    	  PageRequest pageRequest = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "timestamp"));

	          if (start != null) {
	              // If start is provided, adjust the page request
	        	  int n=getTotalEntry();
	        	
	        	  int pageIndex=0;
	        	  int elementIndex=0;
	        	  int flag=0;
	        	  
	        	  while(flag!=1&&elementIndex<n)
	        	  {
	        		  
	        		  pageRequest = PageRequest.of(pageIndex, limit, Sort.by(Sort.Direction.DESC, "timestamp"));
		              List<ChatLogEntry> entriesBeforeStart = chatLogRepository.getChatLogEntry(userId, pageRequest);
		              for (int i = 0; i < entriesBeforeStart.size(); i++) {
		            	  
		            	    if (start.equals(entriesBeforeStart.get(i).getId())) {
		            	    	
		            	    	System.out.println(pageIndex+" "+limit);
		            	        flag=1;
		            	   	 
		            	        break; // Exit the loop once the messageID is found
		            	    }
		            	    elementIndex++;
		              }
		              pageIndex=elementIndex/limit;
	        	  }
	        	 
	           pageRequest = PageRequest.of(pageIndex, limit, Sort.by(Sort.Direction.DESC, "timestamp"));}

	          return chatLogRepository.getChatLogEntry(userId, pageRequest);

	          }
	    
	    public ChatLogEntry createChatLogEntry(ChatLogEntry c) {
	    	userRepository.findById(c.getUserId()).orElseThrow(()->new ResourceNotFoundException());
	        return chatLogRepository.save(c);
	    }

	  
	    public void deleteChatLogEntry(String userId,String messageId) {
	    	 int deletedEntries=chatLogRepository.deleteByUserIdandMessageId(userId,messageId);
	    	 if(deletedEntries==0)
	    		 throw new ResourceNotFoundException();
	    }

	    public void deleteChatLogs(String userId) {
	    	int deletedEntries=chatLogRepository.deleteByUserId(userId);
	    	 if(deletedEntries==0)
	    		 throw new ResourceNotFoundException();
	    }

}
