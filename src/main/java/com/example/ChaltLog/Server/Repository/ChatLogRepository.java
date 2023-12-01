package com.example.ChaltLog.Server.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ChaltLog.Server.Entity.ChatLogEntry;
import com.example.ChaltLog.Server.Entity.User;

import jakarta.transaction.Transactional;



@Repository
public interface ChatLogRepository extends JpaRepository<ChatLogEntry, String> {
	

    @Query("SELECT c FROM ChatLogEntry c WHERE c.userId = :userId order by c.timestamp DESC")
	List<ChatLogEntry> getChatLogEntry(@Param("userId") String userId,Pageable paging);
    
	 @Modifying
	 @Transactional
    @Query("DELETE FROM ChatLogEntry c WHERE c.userId = :userId")
	int deleteByUserId(@Param("userId") String userId);

	 @Modifying
	 @Transactional
    @Query("DELETE FROM ChatLogEntry c WHERE c.userId = :userId and c.Id= :messageId")
	int deleteByUserIdandMessageId(@Param("userId") String userId,@Param("messageId") String messageId);
	 
	 @Query("SELECT count(*) FROM ChatLogEntry c ")
	int getTotalEntry();

}
