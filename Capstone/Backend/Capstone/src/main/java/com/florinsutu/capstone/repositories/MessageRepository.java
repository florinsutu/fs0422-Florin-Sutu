package com.florinsutu.capstone.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Message;
import com.florinsutu.capstone.models.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
    @Query( value = "SELECT m FROM Message m WHERE m.sender.id = :id" )
    Page<Message> findBySender(Long id, Pageable p);
    
    @Query( value = "SELECT m FROM Message m WHERE m.receiver.id = :id" )
    Page<Message> findByReceiver(Long id, Pageable p);

    @Query( value = "SELECT m FROM Message m WHERE"
    		+ " (m.receiver.id = :user1Id OR m.receiver.id = :user2Id)"
    		+ " AND (m.sender.id = :user1Id OR m.sender.id = :user2Id)"
    		+ " ORDER BY m.date" )
	List<Message> findByChat(Long user1Id, Long user2Id);
    
    @Modifying
    @Query( value = "DELETE FROM Message m WHERE m.receiver.id = :id OR m.sender.id = :id")
	void removeChatMessagesByUserId(Long id);
    
    @Query(value = "SELECT Distinct m.receiver FROM Message m WHERE m.sender.id = :id")
	List<User> getReceiversByUserId(Long id);
    
    @Query(value = "SELECT Distinct m.sender FROM Message m WHERE m.receiver.id = :id")
  	List<User> getSendersByUserId(Long id);
}
