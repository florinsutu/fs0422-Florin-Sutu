package com.florinsutu.capstone.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
    @Query( value = "SELECT m FROM Message m WHERE m.sender.id = :id" )
    Page<Message> findBySender(Long id, Pageable p);
    
    @Query( value = "SELECT m FROM Message m WHERE m.receiver.id = :id" )
    Page<Message> findByReceiver(Long id, Pageable p);
}
